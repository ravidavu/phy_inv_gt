package com.pi.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.pi.model.StoreProcess;
import com.pi.service.ProcessService;
import com.sherwin.polling.PollingClient;

@RestController
public class ProcessController {
	@Autowired
	private ProcessService processService;

	@RequestMapping(value = "/listOsProcess", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<StoreProcess> getAllSProcess() {
		List<StoreProcess> storeList = processService.getAllStorePo();
		return storeList;

	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public String approve(@RequestBody StoreProcess processData) {
		List<String> stNoList = processData.getStoreNoList();
		System.out
				.println("controller coming to approve****" + stNoList.size());
		String result = null;
		try {
			result = callToWebService(stNoList);
			//result= "1234";
			processService.storeTaskId(result);
			ModifyXMLFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String callToWebService(List<String> stNoList) throws Exception {
		PollingClient pc = new PollingClient("dev", "PhysicalInventory");
		String fname = "C:\\Users\\rxd876\\Downloads\\new_workspace\\PhysicalInventory-2016\\src\\main\\resources\\PollingFile.xml";
		// String fname = "//resources//PollingFile.xml";
		File f = new File(fname);
		// List<String> storesList = new ArrayList<String>();
		// storesList.add("9953");
		pc.postToStores(stNoList, "LIST");
		pc.write(f);
		String result = pc.postMaintenance();
		return result;
	}

	@RequestMapping(value = "/createStr", method = RequestMethod.POST)
	public ResponseEntity<Void> UpdateStroes(
			@RequestBody StoreProcess processData,
			UriComponentsBuilder ucBuilder) {

		System.out.println(" selected data inside controller >>> "
				+ processData.getStoreNoList().size());
		System.out.println(" unselected data inside controller >>>>>"
				+ processData.getUncheckList().size());
		processService.updateStore(processData);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/listOsProcess").buildAndExpand()
				.toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	public void ModifyXMLFile() {
		try {
			String filepath = "C:\\Users\\rxd876\\Downloads\\new_workspace\\PhysicalInventory-2016\\src\\main\\resources\\PollingFile.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the root element
			// Node data= doc.getFirstChild();

			Node startdate = doc.getElementsByTagName("exec_date").item(0);

			// I am not doing any thing with it just for showing you
			// String currentStartdate = startdate.getNodeValue();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date today = Calendar.getInstance().getTime();

			startdate.setTextContent(df.format(today));

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
