package main.net.bestetti.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jackson.map.ObjectMapper;

import main.net.bestetti.dao.OperationDao;
import main.net.bestetti.model.Operation;

@Path ("/api")
@Produces ({ "application/json" })
public class OperationResource {
	
	@Inject
	OperationDao dao;
	
	@GET
	@Path ("/op/{id}")
	public String printTest(@PathParam("id") Long id) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(df);
		Operation result = dao.getOperationById(id);
		if (result == null) {
			return "No operation with id " + id + " found. Please try another ID";
		} 
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}