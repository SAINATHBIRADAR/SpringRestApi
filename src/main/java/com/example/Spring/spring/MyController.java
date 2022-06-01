package com.example.Spring.spring;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring.entities.Fields;
import com.example.Spring.services.HospitalService;

@RestController
public class MyController {
	
	@Autowired
	private HospitalService hospitalServices;

	@GetMapping("/getkey")
	public long details() {
		return this.hospitalServices.createkey();
	}

	@PostMapping(value = "/getHashedkey", consumes = "application/json")
	public List<?> createHash(@RequestBody Fields fields) throws Exception {
		return this.hospitalServices.createHash(fields);
	}

	@PostMapping(value = "/encryption", consumes = "application/json")
	public List<?> encryption(@RequestBody Fields fields) throws Exception {
		return this.hospitalServices.encryption(fields);
	}

	@PostMapping(value = "/decryption", consumes = "application/json")
	public List<?> decryption(@RequestBody Fields fields) throws Exception {
		return this.hospitalServices.decryption(fields);
	}

}
