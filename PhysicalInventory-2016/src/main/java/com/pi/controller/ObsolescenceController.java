package com.pi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.pi.model.Obsolescence;
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
}
