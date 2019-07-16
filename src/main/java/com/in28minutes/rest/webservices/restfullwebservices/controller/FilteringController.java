package com.in28minutes.rest.webservices.restfullwebservices.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutes.rest.webservices.restfullwebservices.entity.SomeBean;
import com.in28minutes.rest.webservices.restfullwebservices.entity.SomeDynamicBean;

@RestController
public class FilteringController {
	
	MappingJacksonValue mapping;
	Set<String> filterCriteriaSet;
	// static filetering
	@GetMapping("/filtering")
	public SomeBean filtering() {
		return new SomeBean("value1","value2","value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("value12","value22","value32"),new SomeBean("value1","value2","value3")) ;
	}
	
	// dynamic filtering
	
	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue someDynamicBean() {
		SomeDynamicBean dynamicBean = new SomeDynamicBean("value1","value2","value3");
		mapping = new MappingJacksonValue(dynamicBean);
		filterCriteriaSet = new HashSet<String>();
		filterCriteriaSet.add("field1");
		filterCriteriaSet.add("field2");
		doSomeDynamicFiltering(filterCriteriaSet);
		return mapping;
	}
	
	
	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue someDyanmicBeanList() {
		List<SomeDynamicBean> someDynamicBeanList = Arrays.asList(new SomeDynamicBean("value1","value2","value3"), new SomeDynamicBean("value12","value22","value32"));
		mapping = new MappingJacksonValue(someDynamicBeanList);
		filterCriteriaSet = new HashSet<String>();
		filterCriteriaSet.add("field1");
		doSomeDynamicFiltering(filterCriteriaSet);
		return mapping;
		
	}
	
	public void doSomeDynamicFiltering(Set filterCriteria) {
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filterCriteria);
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicBeanFilter", filter );
		mapping.setFilters(filters );
		
	}
}
