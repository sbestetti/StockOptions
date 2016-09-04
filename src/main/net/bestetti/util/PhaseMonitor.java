package main.net.bestetti.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import main.net.bestetti.mb.LoginBean;

public class PhaseMonitor implements PhaseListener {

	private static final long serialVersionUID = 1L;
	
	@Inject
	LoginBean loginBean;
	
	@Override
	public void afterPhase(PhaseEvent arg0) {
		System.out.println(this.getPhaseId() + " login bean status: " + loginBean.isLogged());
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
	

}
