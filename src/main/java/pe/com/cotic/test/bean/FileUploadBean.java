package pe.com.cotic.test.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;


@ManagedBean(name="fileUploadBean")
public class FileUploadBean {
	
	private UploadedFile file;
	private String ruta_destino = System.getProperty("ruta_destino") != null ? System.getProperty("ruta_destino") : "";
	  	
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
	                cargaExcel(file.getFileName());
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
	 * Procesando archivo excel
	 */
	public void cargaExcel(String fileName) {
		List cellData = new ArrayList();
		try {
			FileInputStream fileXls = new FileInputStream(new File(ruta_destino + file.getFileName()));
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Llamamos al método obtener y le pasamos el arreglo cellData
		obtener(cellData);
		
	}
	
	public void obtener(List cellDataList) {
		for (int i=0; i<cellDataList.size(); i++) {
			List cellTempList = (List) cellDataList.get(i);
			for (int j=0; j<cellTempList.size(); j++) {
				XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
				System.out.println("Valor: " + hssfCell.toString());
			}
		}
	}
	
	/*
	 * Funcion para validar documento de identidad peruano
	 */
	/*
	public void documento() {
		if (ValidaIdentificacionPeru(getDni())) {
			FacesMessage message = new FacesMessage("DNI ", " Validado correctamente.");
	        //FacesContext.getCurrentInstance().addMessage("message", message);
			System.out.println("DNI Validado correctamente.");
		} else {
			FacesMessage message = new FacesMessage("DNI ", " Incorrecto o No Valido.");
			FacesContext.getCurrentInstance().addMessage("message", message);
			System.out.println("DNI Invalido o incorrecto.");
		}
	}
	
	
	public boolean ValidaIdentificacionPeru(String identificationDocument) {
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
				if (hashNumbers[addition]==last) respuesta = true;
			} else if (Character.isLetter(last)) {
				char[] hashLetters = { 'K', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
				if (hashLetters[addition]==last) respuesta = true;
			}
		}		
		return respuesta;
	}*/
	
	
	
}
