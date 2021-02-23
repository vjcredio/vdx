package com.vdx.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdx.keystore.KeyStoreManager;

@Service
public class KeyService {

	
	@Autowired
	private KeyStoreManager keyStoreManager;
	
	public Integer getKey(String key) {
		return keyStoreManager.getKey(key);
	}
	public void addAndUpdate(String key, Integer value) {
		keyStoreManager.addKey(key, value);
	}
	public Integer getAverageValue() {
		Map<String,Integer>  nonExpiredKeyValue =keyStoreManager.getNonExpiredValues();
		if(nonExpiredKeyValue.size()==0) {
			return 0;
		}
		Integer sum =0;
		for(String key : nonExpiredKeyValue.keySet()) {
			sum = sum + nonExpiredKeyValue.get(key);
		}
		return (sum/nonExpiredKeyValue.size());
	}

}
