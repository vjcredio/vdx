package com.vdx.keystore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vdx.keystore.entity.KeyStoreValue;

@Component
public class KeyStore {

	@Autowired
	private KeyStoreTimeConfig keyStoreTimeConfig;
	
	private Map<String,KeyStoreValue > store = new ConcurrentHashMap<String,KeyStoreValue>();
	
	public void addKey(String key,Integer value) {
	
		if(!store.containsKey(key)) {
			KeyStoreValue keyStoreValue = new KeyStoreValue();
			Long exipringTime = keyStoreTimeConfig.getExpireTime();
			keyStoreValue.setCurrentTime(System.currentTimeMillis());
			keyStoreValue.setExpiredTime(keyStoreValue.getCurrentTime()+(exipringTime*1000));
			keyStoreValue.setValue(value);
			keyStoreValue.setExpired(false);
			store.put(key, keyStoreValue);
		}else {
			KeyStoreValue keyStoreValue = store.get(key);
			if(!keyStoreValue.isExpired()) {
				boolean expired =_checkexpired(keyStoreValue.getExpiredTime());
				keyStoreValue.setExpired(expired);
			}
			keyStoreValue.setValue(value);
			store.put(key, keyStoreValue);
		}
	}
	
	
	public boolean _checkexpired(Long expiringTime) {
		if(System.currentTimeMillis()>expiringTime) {
			return true;
		}
		return false;
	}
	
	public Integer getValueByKey(String key) {
		if(!store.containsKey(key)) {
			return null;
		}
		KeyStoreValue keyStoreValue =store.get(key);
		if(_checkexpired(keyStoreValue.getExpiredTime())) {
			return null;
		}
		return keyStoreValue.getValue();
	}


	public Map<String, KeyStoreValue> getAllValues() {
		return this.store;
	}
}
