package com.artivistas.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("artivistas")
public class ArtivistasApiProperty {
	
	private String originAllowed = "http://localhost:8000";
	
	private final Security security = new Security();

	public Security getSecurity(){
		return security;
	}
	
	public static class Security{
		
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
		
	}

	public String getOriginAllowed() {
		return originAllowed;
	}

	public void setOriginAllowed(String originAllowed) {
		this.originAllowed = originAllowed;
	}
	
	
}
