package main.net.bestetti.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLParser {
	
	private String company;
	private Double lastPrice;
	private Double change;
	private boolean fetchOk;
	
	public XMLParser(String ticker) {
		
		String uri = "http://dev.markitondemand.com/MODApis/Api/v2/Quote?symbol=" + ticker;
		
		try {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
			InputStream xml = connection.getInputStream();
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(xml);
			if (!(doc.getRootElement().getChild("Status").getText().equals("SUCCESS"))) {
				fetchOk = false;
				return;
			}
			this.fetchOk = true;
			this.company = doc.getRootElement().getChild("Name").getText();
			this.lastPrice = Double.parseDouble(doc.getRootElement().getChild("LastPrice").getText());
			this.change = Double.parseDouble(doc.getRootElement().getChild("Change").getText());
		} catch (MalformedURLException e) {
			System.out.println("XML Parser error: Malformed URL Exception");
		} catch (IOException e) {
			System.out.println("XML Parser error: IO Exception");
		} catch (JDOMException e) {
			System.out.println("XML Parser error: JDOM exception");
		}
	
	}
	
	//Getters & Setters
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public boolean isFetchOk() {
		return fetchOk;
	}

	public void setFetchOk(boolean fetchOk) {
		this.fetchOk = fetchOk;
	}
	
}