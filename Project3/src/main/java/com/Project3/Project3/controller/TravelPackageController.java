package com.Project3.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project3.Project3.model.TravelPackage;
import com.Project3.Project3.service.TravelPackageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TravelPackageController {
	
	@Autowired
	TravelPackageService travelPackageService;
	
	@PostMapping("/travelPost")
	public void travelPost(@RequestBody TravelPackage travelPackage)
	{
		travelPackageService.saveData(travelPackage);
	}
	
	@GetMapping("/travelGet")
	public List<TravelPackage> travelGet()
	{
		return travelPackageService.returnData();
	}
	
	@PostMapping("/packageCreation")
	public void packageCreation(@Valid @RequestBody TravelPackage travelPackage)
	{
		System.out.println("working");
		travelPackageService.createPackage(travelPackage);
	}
	
	@GetMapping("/packageDisplay")
	public List<TravelPackage> packageDisplay()
	{
		return travelPackageService.packageDisplay();
	}
	
	
}
