package com.pi.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.pi.model.Message;
import com.pi.model.StoreProcess;
import com.pi.service.ProcessService;
import com.sherwin.polling.PollingClient;
import com.sherwin.polling.api.InvalidInputException;

@RestController
public class ProcessController {
	@Autowired
	private ProcessService processService;

	@RequestMapping(value = "/listOsProcess", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<StoreProcess> getAllSProcess() {
		System.out.println("ProcessCOntroller...getAllSProcess..");
		List<StoreProcess> storeList = processService.getAllStorePo();
		return storeList;
	}

	/*
	 * @RequestMapping(value = "/listOfReport", method = RequestMethod.GET,
	 * headers = "Accept=application/json") public List<StoreProcess>
	 * getAllReport() {
	 * System.out.println("ProcessCOntroller...listOfReport..");
	 * List<StoreProcess> storeList = processService.getAllReport();
	 * System.out.println("i am here for the report page..."); return storeList;
	 * }
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public ResponseEntity<Message> approve(@RequestBody StoreProcess processData) {

		System.out
				.println(" selected data from text area inside controller >>> "
						+ processData.getStoreNoList().size());
		//processService.updateStore(processData);

		Message msg = new Message();
		String message = "";
		String result = null;
		List<String> stNoList = processData.getStoreNoList();

		try {
			//processService.updateStore(processData);
			//processService.storeTaskId(result);
			result = callToWebService(stNoList);
			processService.storeTaskId(result);
			ModifyXMLFile(processData.getProcessDate());
			message = "Inventory Approved Sucesssfully !!";
			msg.setMessage(message);
			return new ResponseEntity<Message>(msg, HttpStatus.CREATED);
		}catch(InvalidInputException ie){
			message = "Error in Approved !!";
			msg.setMessage(message + "Invalid Input parameters");
			return new ResponseEntity<Message>(msg,
					HttpStatus.FAILED_DEPENDENCY);
		} catch (Exception e) {
			message = "Error in Approved !!";
			msg.setMessage(message + e.getMessage());
			return new ResponseEntity<Message>(msg,
					HttpStatus.FAILED_DEPENDENCY);
		}
	}

	public String callToWebService(List<String> stNoList) throws Exception {

		PollingClient pc = new PollingClient("dev", "PhysicalInventory");
		String fname = "C:\\Users\\rxd876\\Downloads\\new_workspace\\PhysicalInventory-2016\\src\\main\\resources\\PollingFile.xml";
		// String fname = "//resources//PollingFile.xml";
		File f = new File(fname);
		List<String> storesList = new ArrayList<String>();
		//storesList.addAll(stNoList);
		storesList.add("9953");
		pc.postToStores(stNoList, "LIST");
		pc.write(f);
		String result = pc.postMaintenance();
		return result;
	}

	@RequestMapping(value = "/createStr", method = RequestMethod.POST)
	public ResponseEntity<Message> UpdateStroes(
			@RequestBody StoreProcess processData,
			UriComponentsBuilder ucBuilder) {

		System.out.println(" selected data inside controller >>> "
				+ processData.getStoreNoList().size());
		System.out.println(" unselected data inside controller >>>>>"
				+ processData.getUncheckList().size());
		System.out.println("processDate " + processData.getProcessDate());
		processService.updateStore(processData);
		Message msg = new Message();
		String message = "";

		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(ucBuilder
		 * .path("/listOsProcess").buildAndExpand().toUri()); return new
		 * ResponseEntity<Void>(headers, HttpStatus.CREATED);
		 */
		message = "Inventory Saved Sucesssfully !!";
		msg.setMessage(message);
		return new ResponseEntity<Message>(msg, HttpStatus.CREATED);
		// return message;
	}

	/*
	 * @RequestMapping(value = "/createStr", method = RequestMethod.POST) public
	 * ResponseEntity<Void> UpdateStroes(@RequestBody StoreProcess
	 * processData,UriComponentsBuilder ucBuilder) {
	 * 
	 * System.out.println(" selected data inside controller >>> "+
	 * processData.getStoreNoList().size());
	 * System.out.println(" unselected data inside controller >>>>>"+
	 * processData.getUncheckList().size());
	 * System.out.println("processDate "+processData.getProcessDate());
	 * processService.updateStore(processData); HttpHeaders headers = new
	 * HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/listOsProcess").buildAndExpand
	 * ().toUri()); return new ResponseEntity<Void>(headers,
	 * HttpStatus.CREATED); }
	 */
	public void ModifyXMLFile(String date) throws Exception {
		System.out.println("date in modifyxml file");
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

		// DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		// Date today = Calendar.getInstance().getTime();

		startdate.setTextContent(date);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);

		System.out.println("Done");

	}

}
