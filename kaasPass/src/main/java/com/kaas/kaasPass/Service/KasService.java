package com.kaas.kaasPass.Service;

import org.springframework.http.ResponseEntity;

import com.kaas.kaasPass.Model.Tourist;

public interface KasService {
	String save(Tourist tourist) throws Exception;
	
	String retriveTourist(String filePath) throws Exception;
}
