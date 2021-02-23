package com.vdx.keystore;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vdx.keystore.entity.KeyStoreValue;

@Component
public class KeyStoreManager {

	@Autowired
	private KeyStore keyStore;
	
	public void addKey(String key,Integer value) {
		keyStore.addKey(key, value);
	}
	
	public Integer getKey(String key) {
		return keyStore.getValueByKey(key);
	}

	public Map<String, Integer> getNonExpiredValues() {
		Map<String, Integer> nonExpiredValues = new HashMap<String,Integer>();
		Map<String,KeyStoreValue > store = keyStore.getAllValues();
		for(String key : store.keySet()) {
			KeyStoreValue storeValue=store.get(key);
			if(!storeValue.isExpired()) {
				if(!keyStore._checkexpired(storeValue.getExpiredTime())) {
					nonExpiredValues.put(key, storeValue.getValue());
				}
			}
		}
		return nonExpiredValues;
	}

}
