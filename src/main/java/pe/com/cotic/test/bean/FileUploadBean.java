package pe.com.cotic.test.bean;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileUploadBean {

	private UploadedFile file;
	 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
    	
    	try {
            if(file != null) {
            	//FileInputStream fileXls = new FileInputStream(new File("C:\\Users\\Johan\\Documents\\" + file.getFileName()));
            	FileInputStream fileXls = new FileInputStream(new File("C:\\" + file.getFileName()));
            	// Crear el objeto que tendra el libro de Excel
            	HSSFWorkbook workbook = new HSSFWorkbook(fileXls);
            	/*
        		 * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
        		 * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator
        		 * que nos permite recorrer cada una de las filas que contiene.
        		 */
        		HSSFSheet sheet = workbook.getSheetAt(0);
        		Iterator<Row> rowIterator = sheet.iterator();
        		Row row;
       			// Recorremos todas las filas para mostrar el contenido de cada celda
        		while (rowIterator.hasNext()){
        			row = rowIterator.next();
   				    // Obtenemos el iterator que permite recorres todas las celdas de una fila
        			Iterator<Cell> cellIterator = row.cellIterator();
   				    Cell celda;
   				 while (cellIterator.hasNext()){
					celda = cellIterator.next();
					// Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...
					switch(celda.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
						    if( HSSFDateUtil.isCellDateFormatted(celda) ){
						       System.out.println(celda.getDateCellValue());
						    }else{
						       System.out.println(celda.getNumericCellValue());
						    }
						    System.out.println(celda.getNumericCellValue());
						    break;
						case Cell.CELL_TYPE_STRING:
						    System.out.println(celda.getStringCellValue());
						    break;
						case Cell.CELL_TYPE_BOOLEAN:
						    System.out.println(celda.getBooleanCellValue());
						    break;
					}
   				 }
   				    
        		}
        		// cerramos el libro excel
       			workbook.close();
        		
                FacesMessage message = new FacesMessage("Correcto", file.getFileName() + " fue subido.");
                FacesContext.getCurrentInstance().addMessage(null, message);                
                
            }    		
    	} catch (Exception ex) {
    		FacesMessage message = new FacesMessage("Error", file.getFileName() + " no se pudo subir.");
            FacesContext.getCurrentInstance().addMessage(null, message);
    	}

    }
}
