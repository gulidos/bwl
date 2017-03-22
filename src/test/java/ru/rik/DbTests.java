package ru.rik;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ru.rik.bwl.BwlApplication;
import ru.rik.bwl.service.PortRepo;
import ru.rik.bwl.service.WlRepo;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes=BwlApplication.class)
public class DbTests {
	
@Autowired WlRepo wls;
@Autowired PortRepo ports;

	@Test
	public void testWlCountOk() {
		Assert.assertTrue(wls.count() > 0);
	}
	
	@Test
	public void getWlOk() {
		Assert.assertNotNull( wls.findOne("01234567890"));
	}
	
	@Test
	public void getWlNotExists() {
		Assert.assertNull( wls.findOne("01234567800"));
	}
	
	@Test
	public void getPortCount() {
		Assert.assertTrue(ports.count() > 0);
	}
	
	@Test
	public void findPortByNumber() {
		Assert.assertNotNull(ports.findOne("9000000000")); 
	}			
}
