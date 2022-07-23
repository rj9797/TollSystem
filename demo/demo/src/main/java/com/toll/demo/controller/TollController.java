package com.toll.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.toll.demo.service.tollService;

@RestController
@RequestMapping("/tollsystem/v1")
public class TollController {

	@Autowired
	private tollService tollService;
	
	@PostMapping(value = "/tollSystem")
	public ResponseEntity<String> tollSystem(@RequestBody String json) throws Exception {
		
		System.out.println("called");
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date();  
		    System.out.println(date.getHours());
		    System.out.println(json);
		    
			int value=tollService.tollSystem(date, json,"username");
			if(value==0) {
				return ResponseEntity.status(HttpStatus.OK).body("success");
			}else if(value==1) {
				return ResponseEntity.status(HttpStatus.OK).body("wallet is running low on balance. FAILED");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body("");
		 
	}

	
	@PostMapping(value = "/getTotal")
	public ResponseEntity<String> getTotal(@RequestBody String json) throws Exception {
		
		try {
			Map<String,Integer> map=tollService.getTotal();
			JsonObject jsonObj=new JsonObject();
			jsonObj.addProperty("Total vehicles passed", map.get("totalVehiclesPassed"));
			jsonObj.addProperty("Total amount collected", map.get("totalAmountCollected"));
			return ResponseEntity.status(HttpStatus.OK).body(jsonObj.toString());
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

}
