package com.gradle.java.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Users {
	
	private String name;
//	@JSONField(serialize = false)  
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
