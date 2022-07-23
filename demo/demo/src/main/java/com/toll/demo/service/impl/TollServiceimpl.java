package com.toll.demo.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toll.demo.service.tollService;

@Service("TollServiceImpl")
public class TollServiceimpl implements tollService{
	
	@Override
	public int tollSystem(Date exitTime, String vehicleType,String username) {
		try {
			int TRUCK_CHARGE=100;
			int FOUR_WHEELER_CHARGE=60;
			int BUS=80;
			int STANDARD_CHARGES=50;
			//Standard discount
			int discount=10;
			//Wallet balance that we got from the database of that user;
			int walletBalance=500; 
			
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(exitTime);
		    calendar.add(Calendar.DATE, -1);
		    //Assuming this is the time we got from the database
		    Date yesterday = calendar.getTime();
		    
		    
		    long secs = (yesterday.getTime() - exitTime.getTime()) / 1000;
		    //This is the difference of time between his trips
		    int differenceInHours = (int)secs / 3600;   
		   int appliedCharges=0;
		   if(vehicleType.equalsIgnoreCase("Truck")) {
			   appliedCharges=TRUCK_CHARGE;
		   }else if(vehicleType.equalsIgnoreCase("FourWheeler")) {
			   appliedCharges=FOUR_WHEELER_CHARGE;
		   }else if(vehicleType.equalsIgnoreCase("Bus")) {
			   appliedCharges=BUS;
		   }
		   else {
			   appliedCharges= 0;
		   }
		   if(differenceInHours<24) {
			   appliedCharges=(appliedCharges*discount)/100;
		   }
//		   walletBalance=walletBalance-appliedCharges;
		   if(vehicleType.equalsIgnoreCase("Truck") || vehicleType.equalsIgnoreCase("FourWHeeler") ||
				   vehicleType.equalsIgnoreCase("Bus")){
				  if(walletBalance>=appliedCharges ) {
					  walletBalance=walletBalance-appliedCharges;
					  return 0;
				  }else {
					  return 1;
				  }
			   //return and save value in database for that user
		   }else{
			   return 0;
		   }
		   
		}catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public Map<String, Integer> getTotal() {
		Map<String,Integer> vehicleMap=new HashMap<String, Integer>();
		try {
			//Getting the total amount collected and totalVehicles passed from database
			int totalAmountCollected=55000;
			int totalVehiclesPassed= 500;
			vehicleMap.put("totalVehiclesPassed", totalVehiclesPassed);
			vehicleMap.put("totalAmountCollected", totalAmountCollected);
			return vehicleMap;
		}catch (Exception e) {
			throw e;
		}
		
	}

	
}
