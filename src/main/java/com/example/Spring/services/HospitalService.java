package com.example.Spring.services;

import java.util.*;
import com.example.Spring.entities.Fields;

public interface HospitalService {
	public int createkey();

	public List<?> createHash(Fields fields);

	public List<?> encryption(Fields fields) throws Exception;

	public List<?> decryption(Fields fields) throws Exception;
}

