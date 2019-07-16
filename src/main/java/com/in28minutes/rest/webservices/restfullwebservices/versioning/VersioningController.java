package com.in28minutes.rest.webservices.restfullwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	
	// URI Versioning(used by Twitter)
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Nisha Garg");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Nisha","Garg"));
	}
	
	// Request parameters versioning(used by Amazon)
	
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Nisha Garg");
	}
	
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Nisha","Garg"));
	}
	
	// Header Versioning(used by Microsoft)
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Nisha Garg");
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Nisha","Garg"));
	}
	
	// produces or Accept Header Versioning or Content Negotiation or mime type versioning or media type versioning(used by Git hub)
	
	@GetMapping(value="/person/header",produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Nisha Garg");
	}
	
	@GetMapping(value="/person/header",produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Nisha","Garg"));
	}
}
