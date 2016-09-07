package main.net.bestetti.mb;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MenuBean {
	
	@Inject
	private OperationBean operationBean;
	
	public String addOperation() {
		operationBean.cleanBean();
		return "/addoperation.xhtml?faces-redirect=true";
	}

}
