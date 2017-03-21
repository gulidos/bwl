package ru.rik;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ru.rik.bwl.BwlApplication;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes=BwlApplication.class)
public class BwlApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	

}
