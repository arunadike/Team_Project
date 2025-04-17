package com.Project3.Project3.service;
 
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;
 
//import com.Project3.Project3.controller.List;
import java.util.List;
import com.Project3.Project3.model.AssistanceRequest;
import com.Project3.Project3.repository.AssistanceRequestRepository;
 
@Service
public class AssistanceRequestService {
	@Autowired
	AssistanceRequestRepository assistancerequestrepository;
 
	public void saveData(AssistanceRequest assistancerequest) {
		// TODO Auto-generated method stub

		assistancerequestrepository.save(assistancerequest);
	}
 
	public List<AssistanceRequest> returnData() {
		// TODO Auto-generated method stub	
		return (List<AssistanceRequest>) assistancerequestrepository.findAll();
	}

 
}