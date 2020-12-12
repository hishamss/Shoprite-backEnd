package com.revature.shoprite.model;

public class Employee {

	private int id;
	private String names;
	private String jobs;

	public Employee() {
		super();
		this.id = -1;
		this.names = null;
		this.jobs = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getJobs() {
		return jobs;
	}

	public void setJobs(String jobs) {
		this.jobs = jobs;
	}

}
