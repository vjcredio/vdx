package com.vdx.keystore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyStoreTimeConfig {

	@Value("${timeInHr}")
    private Long timeInhr;
	
	@Value("${timeInMin}")
    private Long timeInMin;
	
	@Value("${timeInSec}")
    private Long timeInSec;
	
	private Long expireTimeInSec;
	
	
	public Long getExpireTime() {
		if(expireTimeInSec!=null) {
			return expireTimeInSec;
		}
		
		this.expireTimeInSec= this.timeInhr*60*60 + this.timeInMin*60 +this.timeInSec;
		
		return this.expireTimeInSec;
	}
}
