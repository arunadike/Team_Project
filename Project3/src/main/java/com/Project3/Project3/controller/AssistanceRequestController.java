package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.AssistanceRequest;
import com.Project3.Project3.service.AssistanceRequestService;

@RestController
public class AssistanceRequestController {

	@Autowired
	AssistanceRequestService assistancerequestservice;

	@PostMapping("/AssistanceReqPost")
	public void AssistanceReqPost(@RequestBody AssistanceRequest assistancerequest) {
		assistancerequestservice.saveData(assistancerequest);
	}

	@GetMapping("/AssistanceReqGet")
	public List<AssistanceRequest> AssistanceReqGet() {
		return assistancerequestservice.returnData();
	}

}
