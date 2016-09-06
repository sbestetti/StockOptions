package main.net.bestetti.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import main.net.bestetti.mb.LoggedUserBean;

public class JSFLoginChecker implements PhaseListener {

	private static final long serialVersionUID = 1L;
	
	@Inject
	LoggedUserBean loggedUserBean;
	
	@Override
	public void afterPhase(PhaseEvent arg0) {
		
		FacesContext context = arg0.getFacesContext();
		
		if ("/index.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		}
		
		if (loggedUserBean.isLogged()) {
			return;
		} else {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
			context.renderResponse();			
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		return;		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}