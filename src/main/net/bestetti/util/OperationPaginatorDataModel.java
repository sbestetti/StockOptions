package main.net.bestetti.util;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import main.net.bestetti.dao.OperationDao;
import main.net.bestetti.mb.LoginBean;
import main.net.bestetti.model.Operation;

@Named @SessionScoped
public class OperationPaginatorDataModel extends LazyDataModel<Operation> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private OperationDao dao;
	
	@Inject
	private LoginBean loginBean;
	
	private Integer totalResults;
	
	@PostConstruct
	void setTotal() {
		totalResults = dao.operationCount(loginBean.getUser());
		super.setRowCount(totalResults);		
	}

	@Override
	public List<Operation> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		return dao.getPagedResult(loginBean.getUser(), first, pageSize);
	}

	//Getters & Setters
	public Integer getTotalResults() {
		return totalResults;
	}

}