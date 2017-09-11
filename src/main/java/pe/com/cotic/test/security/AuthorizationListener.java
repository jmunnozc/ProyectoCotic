package pe.com.cotic.test.security;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();
		boolean isLoginPage = (currentPage.lastIndexOf("index.jsf") > -1) ? true : false;
		HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(true);
		Object usuario = sesion.getAttribute("usuario");
		
		if (!isLoginPage && usuario == null) {
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "/index.jsf");
			
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}
	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}


}
