package com.kaas.kaasPass.Controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaas.kaasPass.Model.Address;
import com.kaas.kaasPass.Model.Tourist;
import com.kaas.kaasPass.Service.KasService;

@RestController
public class Controller {
	
	@Autowired
	KasService kasService;
	
	@PostMapping("/registration")
	public String registration(@RequestBody Tourist tourist) throws Exception{
		return kasService.save(tourist);//new ResponseEntity<Tourist>(kasService.save(tourist), HttpStatus.OK);
	}
	
	@PostMapping("/retrive")
	public String retriveTourist() throws Exception {
		String filePath = "/Users/ajaykokare/Documents/KasProject/QrCodes/9012345670.png";
		String s = kasService.retriveTourist(filePath);
		return s;
	}
	
	@PostMapping("/address")
	public ResponseEntity<Address> address (@RequestBody Address address){
		return new ResponseEntity<Address>(address,HttpStatus.OK);
	}
	
}
