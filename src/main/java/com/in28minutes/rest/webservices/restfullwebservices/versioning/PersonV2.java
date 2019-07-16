package com.in28minutes.rest.webservices.restfullwebservices.versioning;

public class PersonV2 {
	private Name name;

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonV2 [name=" + name + "]";
	}

	public PersonV2(Name name) {
		super();
		this.name = name;
	}
	
	public PersonV2() {
		
	}
}
