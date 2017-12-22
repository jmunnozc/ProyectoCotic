package pe.com.cotic.test.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

@ManagedBean(name="fileUploadBean")
@SessionScoped
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
                FacesMessage message = new FacesMessage("Correcto", file.getFileName() + " fue subido.");
                FacesContext.getCurrentInstance().addMessage(null, message);                
                
            }    		
    	} catch (Exception ex) {
    		FacesMessage message = new FacesMessage("Error", file.getFileName() + " no se pudo subir.");
            FacesContext.getCurrentInstance().addMessage(null, message);
    	}

    }
}
