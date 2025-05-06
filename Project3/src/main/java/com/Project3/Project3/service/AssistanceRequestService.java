package com.Project3.Project3.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project3.Project3.model.AssistanceRequest;
import com.Project3.Project3.repository.AssistanceRequestRepository;

@Service
public class AssistanceRequestService {

	private static final Logger logger = LoggerFactory.getLogger(AssistanceRequestService.class);

	@Autowired
	AssistanceRequestRepository assistancerequestrepository;

	public void saveData(AssistanceRequest assistancerequest) {
		try {
			assistancerequestrepository.save(assistancerequest);
			logger.info("AssistanceRequest saved successfully");
		} catch (Exception e) {
			logger.error("Error saving AssistanceRequest: {}", e.getMessage(), e);
			throw e;
		}
	}

	public List<AssistanceRequest> returnData() {
		try {
			return (List<AssistanceRequest>) assistancerequestrepository.findAll();
		} catch (Exception e) {
			logger.error("Error fetching AssistanceRequests: {}", e.getMessage(), e);
			throw e;
		}
	}
}
