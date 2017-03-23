package ru.rik;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.benmanes.caffeine.cache.Cache;

import ru.rik.bwl.BwlApplication;
import ru.rik.bwl.domain.WhiteNumber;
import ru.rik.bwl.service.Requests;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes=BwlApplication.class)
public class CacheTests {
	
@Autowired CacheManager cacheManager;
@Autowired Requests r;


	@SuppressWarnings("unchecked")
	@Test
	public void testWlCache() {
		System.out.println("Using cache Manager" + cacheManager.getClass().getSimpleName());
		org.springframework.cache.Cache cache = cacheManager.getCache("wl");
		
		for (int i = 0; i <10; i++) 
			r.requestWl(String.valueOf(i));

		for (int i = 0; i <5; i++) 
			r.requestWl(String.valueOf(i));

        Cache<String, WhiteNumber> nativeCoffeeCache = (Cache<String, WhiteNumber>) cache.getNativeCache();
        System.out.println(nativeCoffeeCache.toString());
        
        System.out.println(nativeCoffeeCache.stats().toString());        
	}
	
	
}
