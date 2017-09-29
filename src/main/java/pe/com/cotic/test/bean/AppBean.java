package pe.com.cotic.test.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import pe.com.cotic.test.dao.UsuarioDao;
import pe.com.cotic.test.daoImpl.UsuarioDaoImpl;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.MyUtil;

@ManagedBean
@ApplicationScoped
public class AppBean implements Serializable {

	public AppBean(){
		
	}
	
	public String getBaseUrl() {
		return MyUtil.baseurl();
	}
	
	public String getBasePath() {
		return MyUtil.basepath();
	}
	
}
