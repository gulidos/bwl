package ru.rik.bwl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ru.rik.bwl.domain.WhiteNumber;

@Component
public class Requests {
	@Autowired 
	WlRepo wlrepo;
	@Autowired 
	CounterService counterService;

	public Requests() {}
	
	@Cacheable(cacheNames="wl")
	public WhiteNumber requestWl(String n) {
		counterService.increment("bwl.db.white_number_req");
		return wlrepo.findOne(n);
	}

}
