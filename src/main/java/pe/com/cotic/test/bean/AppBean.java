package pe.com.cotic.test.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import pe.com.cotic.test.util.MyUtil;

@ManagedBean
@ApplicationScoped
public class AppBean {

	public AppBean(){
		
	}
	
	public String getBaseUrl() {
		return MyUtil.baseurl();
	}
	
	public String getBasePath() {
		return MyUtil.basepath();
	}
}
