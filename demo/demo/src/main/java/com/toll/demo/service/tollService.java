package com.toll.demo.service;

import java.util.Date;
import java.util.Map;


public interface tollService {
	public int tollSystem(Date enterTime,String vehicleType,String username);
	
	public Map<String,Integer> getTotal();
}
