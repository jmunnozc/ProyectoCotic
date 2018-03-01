package pe.com.cotic.test.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import pe.com.cotic.test.dao.AlternativaDao;
import pe.com.cotic.test.dao.PortafolioDao;
import pe.com.cotic.test.dao.PreguntaDao;
import pe.com.cotic.test.daoImpl.AlternativaDaoImpl;
import pe.com.cotic.test.daoImpl.PortafolioDaoImpl;
import pe.com.cotic.test.daoImpl.PreguntaDaoImpl;
import pe.com.cotic.test.modelo.Alternativa;
import pe.com.cotic.test.modelo.Nivel;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Pregunta;
import pe.com.cotic.test.modelo.Usuario;


@ManagedBean(name="fileUploadBean")
public class FileUploadBean {
	
	private UploadedFile file;
	private String ruta_destino = System.getProperty("ruta_destino") != null ? System.getProperty("ruta_destino") : "";
	
	private String identificationComponent;
	private String dni;
	  	
    public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getIdentificationComponent() {
		return identificationComponent;
	}

	public void setIdentificationComponent(String identificationComponent) {
		this.identificationComponent = identificationComponent;
	}

	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
    	
    	try {
            if(file != null) {
            	String[] extension = file.getFileName().split("\\.");            	
            	if (extension[1].toString().toUpperCase().equals("XLS") || extension[1].toString().toUpperCase().equals("XLSX")) {
	            	copyFile(file.getFileName(), file.getInputstream());
	                FacesMessage message = new FacesMessage("Correcto", file.getFileName() + " fue subido.");
	                FacesContext.getCurrentInstance().addMessage(null, message);
	                
	                File f = new File(ruta_destino + file.getFileName());
	                if (f.exists() && extension[1].toString().toUpperCase().equals("XLSX")) {
	                	cargaExcel(f);
	                } else if (f.exists() && extension[1].toString().toUpperCase().equals("XLS")) {
	                	cargaExcel_xls(f);
	                }
	                
	                
	                message = new FacesMessage("Correcto", file.getFileName() + " fue cargado.");
	                FacesContext.getCurrentInstance().addMessage(null, message);
            	} else {
            		FacesMessage message = new FacesMessage("Error", file.getFileName() + " no es el archivo correcto... verifique");
                    FacesContext.getCurrentInstance().addMessage(null, message);
            	}
                
            }    		
    	} catch (Exception ex) {
    		FacesMessage message = new FacesMessage("Error", file.getFileName() + " no se pudo subir.");
            FacesContext.getCurrentInstance().addMessage(null, message);
    	}

    }   

	
/*	public void upload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Correcto", event.getFile().getFileName() + " fue subido.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        try {
        	copyFile(event.getFile().getFileName(), (InputStream) event.getFile().getInputstream());
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}*/
	
	
	public void copyFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(ruta_destino + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
            
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }          
            in.close();
            out.flush();
            out.close();
          
            System.out.println("Archivo Nuevo Creado...!!!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/*
	 * Procesando archivo excel XLSX
	 */
	public void cargaExcel(File fileName) {
		List cellData = new ArrayList();
		try {
			FileInputStream fileXls = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fileXls);
			
			XSSFSheet hssfSheet = workbook.getSheetAt(0);
			
			Iterator rowIterator = hssfSheet.rowIterator();
			
			while(rowIterator.hasNext()){
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				//Almacenamos los datos en el Iterator
				Iterator iterator = hssfRow.cellIterator();
				List cellTemp = new ArrayList();
				//Nos movemos por los datos de cada celda
				while (iterator.hasNext()) {
					//Almacenamos los datos de cada celda en el hssfCell
					XSSFCell hssfCell = (XSSFCell) iterator.next();
					//Y los datos almacenados en el hssfCell los pasamos al cellTemp
					cellTemp.add(hssfCell);
				}
				//Finalmente los datos almacenados en cellTemp los pasamos en cellData
				cellData.add(cellTemp);
			}
			
			//Llamamos al método obtener y le pasamos el arreglo cellData			
			obtener(cellData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * Procesando archivo excel XLS
	 */
	public void cargaExcel_xls(File fileName) {
		List cellData = new ArrayList();
		try {
			FileInputStream fileXls = new FileInputStream(fileName);
			HSSFWorkbook workbook = new HSSFWorkbook(fileXls);
			
			HSSFSheet hssfSheet = workbook.getSheetAt(0);
			
			Iterator rowIterator = hssfSheet.rowIterator();
			
			while(rowIterator.hasNext()){
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();
				//Almacenamos los datos en el Iterator
				Iterator iterator = hssfRow.cellIterator();
				List cellTemp = new ArrayList();
				//Nos movemos por los datos de cada celda
				while (iterator.hasNext()) {
					//Almacenamos los datos de cada celda en el hssfCell
					HSSFCell hssfCell = (HSSFCell) iterator.next();
					//Y los datos almacenados en el hssfCell los pasamos al cellTemp
					cellTemp.add(hssfCell);
				}
				//Finalmente los datos almacenados en cellTemp los pasamos en cellData
				cellData.add(cellTemp);
			}
			
			//Llamamos al método obtener y le pasamos el arreglo cellData			
			obtener(cellData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void obtener(List cellDataList) {
		String accionBD = "";
		String cuestionario = "";
		String preguntas = "";
		String celdaExcel = "";
		int ultimoPortafolio = 0;
		int ultimaPregunta = 0;		
		String[] extension = file.getFileName().split("\\.");
		
		for (int i=0; i<cellDataList.size(); i++) {
			List cellTempList = (List) cellDataList.get(i);
			System.out.println("--- Inicio ---");
			for (int j=0; j<cellTempList.size(); j++) {
				
				if (extension[1].toUpperCase().equals("XLSX")) {		
					XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
					celdaExcel = hssfCell.toString();
				} else if (extension[1].toUpperCase().equals("XLS")) {
					HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
					celdaExcel = hssfCell.toString();
				}
					
				System.out.println("Valor: [" + i + "][" + j + "]" + celdaExcel.toString());
				if (i==0 && j==0) {
					accionBD = celdaExcel.toString().toUpperCase();
				}
				
				// Crea un Portafolio NUEVO
				if ( accionBD.equals("NUEVO") ) {
					if (i==1 && j==0) {
						cuestionario = celdaExcel.toString();
					}
					if (i==1 && j==1) {
						//Subir Cuestionario y Obtener el codigo del ÚLTIMO Cuestionario subido
						Portafolio port = new Portafolio();
						port.setTituloPortafolio(celdaExcel.toString());
						port.setDescripcionPortafolio(celdaExcel.toString());
						Date today = new Date();
						String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(today);
						port.setFechaInicioPortafolio(fechaActual);
						port.setFlagMostrarRespuestaCorrectaPortafolio(1);
						port.setTiempoPortafolio(15);
						port.setEstado(1);
						Nivel niv = new Nivel();
						niv.setCodigoNivel(3);
						port.setNivel(niv);
						Usuario usuario = null;
						usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
						port.setUsuario(usuario);
						
						PortafolioDao portafolioDao = new PortafolioDaoImpl();
						if (portafolioDao.grabarPortafolio(port)) {
							ultimoPortafolio = portafolioDao.ultimoPortafolio();
						}
						System.out.println("[" + celdaExcel.toString() + "] - Ultimo Portafolio: " + ultimoPortafolio);
					}
					if (i==2 && j==0) {
						preguntas = celdaExcel.toString();
						System.out.println("[" + celdaExcel.toString() + "]");
					}
					if (i>2) {					
						// Subir Pregunta y Obtener el codigo de la ÚLTIMA pregunta subida
						if (j==0) {
							if ( celdaExcel.toString().equals("EOF o FIN") ) {
								Integer.parseInt( "hola" );
							} else {
								Pregunta preg = new Pregunta();
								preg.setTituloPregunta(celdaExcel.toString());
								Portafolio port = new Portafolio();
								port.setCodigoPortafolio(ultimoPortafolio);
								preg.setPortafolio(port);
								preg.setEstado(1);
								PreguntaDao preguntaDao = new PreguntaDaoImpl();
								if (preguntaDao.grabarPregunta(preg)) {
									ultimaPregunta = preguntaDao.ultimaPregunta();
								}
								System.out.println("Ultima Pregunta: " + ultimaPregunta);
							}							
						}
						//Subir Alternativa y Obtener el codigo de la ÚLTIMA alternativa subida
						if (j>0) {
							Alternativa alte = new Alternativa();
							alte.setTituloAlternativa(celdaExcel.toString());
							if (j==1) alte.setFlagRespuestaCorrectaAlternativa(1); else alte.setFlagRespuestaCorrectaAlternativa(0);
							Pregunta preg = new Pregunta();
							preg.setCodigoPregunta(ultimaPregunta);
							alte.setPregunta(preg);
							alte.setEstado(1);
							AlternativaDao alternativaDao = new AlternativaDaoImpl();
							alternativaDao.grabarAlternativa(alte);
						}
					}
				}
				
				
				// Agrega Preguntas y Alternativas a un Portafolio EXISTENTE
				if ( accionBD.equals("EXISTENTE") ) {
					if (i==1 && j==0) {
						cuestionario = celdaExcel.toString();
					}
					if (i==1 && j==1) {
						//Subir Cuestionario y Obtener el codigo del ÚLTIMO Cuestionario subido
						ultimoPortafolio = (int) Double.parseDouble(celdaExcel);
						System.out.println("[" + celdaExcel.toString() + "] - Ultimo Portafolio: " + ultimoPortafolio);
					}
					if (i==2 && j==0) {
						preguntas = celdaExcel.toString();
						System.out.println("[" + celdaExcel.toString() + "]");
					}
					if (i>2) {					
						// Subir Pregunta y Obtener el codigo de la ÚLTIMA pregunta subida
						if (j==0) {
							if ( celdaExcel.toString().equals("EOF o FIN") ) {
								Integer.parseInt( "hola" );
							} else {
								Pregunta preg = new Pregunta();
								preg.setTituloPregunta(celdaExcel.toString());
								Portafolio port = new Portafolio();
								port.setCodigoPortafolio(ultimoPortafolio);
								preg.setPortafolio(port);
								preg.setEstado(1);
								PreguntaDao preguntaDao = new PreguntaDaoImpl();
								if (preguntaDao.grabarPregunta(preg)) {
									ultimaPregunta = preguntaDao.ultimaPregunta();
								}
								System.out.println("Ultima Pregunta: " + ultimaPregunta);
							}							
						}
						//Subir Alternativa y Obtener el codigo de la ÚLTIMA alternativa subida
						if (j>0) {
							Alternativa alte = new Alternativa();
							alte.setTituloAlternativa(celdaExcel.toString());
							if (j==1) alte.setFlagRespuestaCorrectaAlternativa(1); else alte.setFlagRespuestaCorrectaAlternativa(0);
							Pregunta preg = new Pregunta();
							preg.setCodigoPregunta(ultimaPregunta);
							alte.setPregunta(preg);
							alte.setEstado(1);
							AlternativaDao alternativaDao = new AlternativaDaoImpl();
							alternativaDao.grabarAlternativa(alte);
						}
					}
				}
				
			}
			System.out.println("--- Fin ---");
		}
	}
	
	/*
	 * Funcion para validar documento de identidad peruano
	 */
	public void documento() {
		System.out.println("Probando");
		FacesMessage message;
		boolean loggedIn;
		RequestContext context = RequestContext.getCurrentInstance();
		if (ValidaIdentificacionPeru(getDni())) {
			message = new FacesMessage("DNI", " Validado correctamente.");
	        //FacesContext.getCurrentInstance().addMessage("mensajes", message);	        
	        FacesContext.getCurrentInstance().addMessage("mensajes", message);
			context.addCallbackParam("loggedIn", true);
	        
			System.out.println("DNI Validado correctamente.");
		} else {
			message = new FacesMessage("DNI", " Incorrecto o No Valido.");
			//FacesContext.getCurrentInstance().addMessage("mensajes", message);
			
	        FacesContext.getCurrentInstance().addMessage("mensajes", message);
			context.addCallbackParam("loggedIn", false);
			System.out.println("DNI Invalido o incorrecto.");
		}
	}
	
	
	public boolean ValidaIdentificacionPeru(String identificationDocument) {
		FacesMessage message;
		boolean respuesta = false;
		if ( (!identificationDocument.isEmpty()) || (identificationDocument!=null))  {
			int addition = 0;
			int[] hash = {5,4,3,2,7,6,5,4,3,2};
			int identificationDocumentLength = identificationDocument.length();
			setIdentificationComponent(identificationDocument.substring(0, identificationDocumentLength - 1));
			int identificationComponentLength = identificationComponent.length();
			int diff = hash.length - identificationComponentLength;
			
			for (int i = identificationComponentLength-1; i >= 0; i--) {
				addition += (Integer.parseInt("0" + getIdentificationComponent().charAt(i))) * hash[i+diff];
			}
			
			addition = 11 - (addition % 11);
			if (addition == 11) addition = 0;
		
			char last = identificationDocument.charAt(identificationDocumentLength-1);			
			if (Character.isDigit(last)) {
				char[] hashNumbers = { '6', '7', '8', '9', '0', '1', '1', '2', '3', '4', '5' };
				System.out.println("DNI [: " + identificationDocument.substring(0, identificationDocumentLength - 1) + "] y su código seria: [" + hashNumbers[addition] + "]");
				if (hashNumbers[addition]==last) respuesta = true;
			} else if (Character.isLetter(last)) {
				char[] hashLetters = { 'K', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
				System.out.println("DNI [: " + identificationDocument.substring(0, identificationDocumentLength - 1) + "] y su código seria: [" + hashLetters[addition] + "]");
				if (hashLetters[addition]==last) respuesta = true;
			}
		}		
		return respuesta;
	}
	
	
	
}
