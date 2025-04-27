package com.Project3.Project3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.repository.TravelPackageRepository;

@Service
public class TravelPackageService {

	@Autowired
	TravelPackageRepository travelPackageRepository;

	public void saveData(TravelPackage travelPackage) {
		// TODO Auto-generated method stub
		((CrudRepository<TravelPackage, Integer>) travelPackageRepository).save(travelPackage);


	}
	public List<TravelPackage> returnData()
	{
		return (List<TravelPackage>) ((CrudRepository<TravelPackage, Integer>) travelPackageRepository).findAll();
	}
	public void createPackage(TravelPackage travelPackage) {
		// TODO Auto-generated method stub
		       travelPackageRepository.save(travelPackage);


	}
	public List<TravelPackage> packageDisplay() {
		// TODO Auto-generated method stub
		return (List<TravelPackage>) travelPackageRepository.findAll();
		//return null;
	}

	public Optional<TravelPackage> getPackageById(int packageId) {
		return travelPackageRepository.findById(packageId);
	}

}
