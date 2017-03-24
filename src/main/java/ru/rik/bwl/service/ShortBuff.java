package ru.rik.bwl.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Service
public class ShortBuff {
	private Cache<String, Date> cache;
	@Value("${short_buffer.keep_seconds:60}")	private int keepSeconds;
	
	@Autowired
	public ShortBuff(MetricRegistry m) {
		m.register("shortBuffer.hitCount",(Gauge <Long>) () -> cache.stats().hitCount());
		m.register("shortBuffer.hitRate",(Gauge <Double>) () -> cache.stats().hitRate());
		m.register("shortBuffer.missCount",(Gauge <Long>) () -> cache.stats().missCount());
		m.register("shortBuffer.missRate",(Gauge <Double>) () -> cache.stats().missRate());
		m.register("shortBuffer.evictionCount",(Gauge <Long>) () -> cache.stats().evictionCount());
	}
	
	public ShortBuff(int sec) {
		keepSeconds = sec;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Building cache... keepSeconds " + keepSeconds);
		cache = Caffeine.newBuilder()
				.initialCapacity(1000)
				.recordStats()
				.expireAfterWrite(keepSeconds, TimeUnit.SECONDS)
				.build();
	}
	
	public boolean in(String n) {
		if (cache.getIfPresent(n) == null) { 
			cache.put(n, new Date());
			return false;
		} else 
			return true;
	}
	
	
	public String getStat() {
		return cache.stats().toString() + " size: " + cache.estimatedSize() ;
	}

}
