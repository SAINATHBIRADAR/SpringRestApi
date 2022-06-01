package com.example.Spring.entities;

import java.security.SecureRandom;

public class Fields {

	private char[] password;
	private byte[] initializationVectors;
	private String cipherStr;
	private String sc1 = "";
	private byte[] salt = { 123 };
	private int iterations = 10;
	private int keylength = 256;
	private String plainText;
	private String sc = "";
	private byte[] initializationVector;

	public static byte[] createInitializationVector() {

		byte[] initializationVector = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(initializationVector);
		return initializationVector;
	}

	public static byte[] createInitializationVectors() {

		byte[] initializationVectors = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(initializationVectors);
		return initializationVectors;
	}

	public String getCipherStr() {
		return cipherStr;
	}

	public void setCipherStr(String cipherStr) {
		this.cipherStr = cipherStr;
	}

	public String getSc1() {
		return sc1;
	}

	public void setSc1(String sc1) {
		this.sc1 = sc1;
	}

	public byte[] getInitializationVectors() {
		return initializationVectors;
	}

	public void setInitializationVectors(byte[] initializationVectors) {
		this.initializationVectors = initializationVectors;
	}

	public Fields(char[] password, byte[] salt, int iterations, int keylength, String plainText, String sc,
			byte[] initializationVector, byte[] cipherText, String sc1, byte[] initializationVectors,
			String cipherStr) {
		super();
		System.out.println("asfasdfa");
		this.password = password;
		this.salt = salt;
		this.iterations = iterations;
		this.keylength = keylength;
		this.plainText = plainText;
		this.cipherStr = cipherStr;
		this.initializationVectors = createInitializationVectors();
		this.sc1 = sc1;
		this.initializationVector = createInitializationVector();
	}

	public Fields() {
		super();

	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public int getKeylength() {
		return keylength;
	}

	public void setKeylength(int keylength) {
		this.keylength = keylength;
	}

	public String getPlainText() {
		return plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public byte[] getInitializationVector() {
		return initializationVector;
	}

	public void setInitializationVector(byte[] initializationVector) {
		this.initializationVector = initializationVector;
	}

	public String getSc() {
		return sc;
	}

	public void setSc(String sc) {
		this.sc = sc;
	}

}
