package org.apache.xmlrpc.demo;

public class Calculator {
	public int add(int i1, int i2) {
		System.out.println("Calculator.add");
		return i1 + i2;
	}

	public int subtract(int i1, int i2) {
		System.out.println("Calculator.subtract");
		return i1 - i2;
	}
}