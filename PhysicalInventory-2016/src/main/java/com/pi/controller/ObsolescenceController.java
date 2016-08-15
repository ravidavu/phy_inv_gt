package com.pi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.pi.model.Message;
import com.pi.model.Obsolescence;
import com.pi.model.StoreProcess;
import com.pi.service.ObsolescenceService;

@RestController
public class ObsolescenceController {
	@Autowired
	private ObsolescenceService obsolService;

	@RequestMapping(value = "/getAllObs", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Obsolescence> getAllObsolence() {
		List<Obsolescence> obsList = obsolService.getAllObsolence();
		return obsList;
	}
	@RequestMapping(value = "/deleteObso", method = RequestMethod.POST)
	 public ResponseEntity<Message>   approve(@RequestBody Obsolescence obsolence){
		System.out.println("no of records "+obsolence.getSkuNoList().size());
		obsolService.deleteObsolence(obsolence);
		Message msg = new Message();
		String message ="";
		message="Deleted Successfully !!";
		msg.setMessage(message);
		return new ResponseEntity<Message> (msg, HttpStatus.OK);
	}
}
