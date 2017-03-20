package ru.rik;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import ru.rik.bwl.service.ShortBuff;

public class OneMinuteBuffTests {

	@Test
	public void test() throws InterruptedException {
		ShortBuff buff = new ShortBuff(1);
		buff.init(); 
		Assert.assertFalse(buff.in("111"));
		
		TimeUnit.MILLISECONDS.sleep(100);
		Assert.assertTrue(buff.in("111"));

		TimeUnit.MILLISECONDS.sleep(901);
		Assert.assertFalse(buff.in("111"));

		System.out.println(buff.getStat());
		
	}

}
