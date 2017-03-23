package ru.rik.bwl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ru.rik.bwl.domain.WhiteNumber;

@Component
public class Requests {
	@Autowired WlRepo wlrepo;
	@Autowired PortRepo ports;


	public Requests() {}
	
	@Cacheable(cacheNames="wl")
	public WhiteNumber requestWl(String n) {
		return wlrepo.findOne(n);
	}

}
