package com.vdx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdx.service.KeyService;

@RestController
@RequestMapping("/vdx/key")
public class KeyController {

	
	@Autowired
	private KeyService keyService;
	
	@GetMapping("/{key}")
	public ResponseEntity<?> getKey(@PathVariable("key") String key){
		Integer value =keyService.getKey(key);
		return new ResponseEntity<>(value, HttpStatus.OK);
	}
	
	
	@PutMapping("/{key}/value/{value}")
	public ResponseEntity<?> addAndUpdate(@PathVariable("key")String key, @PathVariable("value") Integer value){
		keyService.addAndUpdate(key,value);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@GetMapping("/average")
	public ResponseEntity<?> getAverage(){
		Integer average = keyService.getAverageValue();
		return new ResponseEntity<>(average, HttpStatus.OK);
	}
}
