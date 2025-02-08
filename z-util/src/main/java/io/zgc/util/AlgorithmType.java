package io.zgc.util;

public enum AlgorithmType {
	HS256(1, 32, "HS256")
	;
	private int code;
	private int length;
	private String name;
	private AlgorithmType(int code, int length, String name) {
		this.code = code;
		this.length = length;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public int getLength() {
		return length;
	}
	public String getName() {
		return name;
	}
	
}