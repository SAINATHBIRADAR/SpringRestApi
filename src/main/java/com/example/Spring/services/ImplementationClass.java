package com.example.Spring.services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.Spring.entities.Fields;
import java.util.Base64;

@Service
public class ImplementationClass implements HospitalService {
	static byte[] invvector = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

	@Override
	public Long createkey() {
		SecureRandom secureRandom = new SecureRandom();
		return secureRandom.nextLong();
	}

	@Override
	public List<Object> createHash(Fields fields) {
		List<Object> list = new ArrayList<>();
		final char[] name = fields.getPassword();
		System.out.print(fields.getPassword());
		final byte[] salts = fields.getSalt();
		final int iterations = fields.getIterations();
		final int key = fields.getKeylength();
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(name, salts, iterations, key);
			SecretKey key1 = skf.generateSecret(spec);
			byte[] res = key1.getEncoded();
			String a = "";
			for (byte b : res) {
				String st = String.format("%02X", b);
				a += st;
			}
			list.add(a);
			SecretKey originalKey = new SecretKeySpec(res, 0, res.length, "AES");
			String encodedKey = Base64.getEncoder().encodeToString(originalKey.getEncoded());
			list.add(encodedKey);

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException();
		}
		return list;
	}

	@Override
	public List<Object> encryption(Fields fields) throws Exception {
		List<Object> list1 = new ArrayList<>();
		final String plaintext = fields.getPlainText();
		final String secret = fields.getSc();
		byte[] decodedKey = Base64.getDecoder().decode(secret);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		byte[] initializationVector = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(initializationVector);
		final byte[] intialization = initializationVector;
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(invvector);
		cipher.init(Cipher.ENCRYPT_MODE, originalKey, ivParameterSpec);
		byte[] cipherText = cipher.doFinal(plaintext.getBytes());
		
		String ciphertexString = "";
		for (byte b : cipherText) {
			String st = String.format("%02X", b);
			ciphertexString += st;
		}
		
		list1.add(ciphertexString);
		return list1;
	}

	@Override
	public List<Object> decryption(Fields fields) throws Exception {
		List<Object> list2 = new ArrayList<>();

		final String cipStr = fields.getCipherStr();
		System.out.println(fields.getCipherStr());
		byte[] ciphers = new byte[cipStr.length() / 2];
		for (int i = 0; i < ciphers.length; i++) {
			int index = i * 2;
			int val = Integer.parseInt(cipStr.substring(index, index + 2), 16);
			ciphers[i] = (byte) val;
		}
		final String derckey = fields.getSc1();
		byte[] decodedKey = Base64.getDecoder().decode(derckey);
		SecretKey originalKey1 = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		byte[] initializationVector1 = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(initializationVector1);
		final byte[] initialization1 = initializationVector1;
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

		IvParameterSpec ivParameterSpec = new IvParameterSpec(invvector);

		cipher.init(Cipher.DECRYPT_MODE, originalKey1, ivParameterSpec);

		byte[] result = cipher.doFinal(ciphers);
		list2.add(new String(result));

		return list2;
	}
}
