package com.example.demo.web.controller;

import java.util.HashMap;
import java.util.Map;

public class ResponseMap {

	private Map<String, Object> map = null;
	
	public ResponseMap() {
		this.map = new HashMap<String, Object>();
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
}
