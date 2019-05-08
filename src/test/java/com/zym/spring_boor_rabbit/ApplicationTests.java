package com.zym.spring_boor_rabbit;

import com.zym.sender.MySender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	MySender sender;

	@Test
	public void myDemo() {
		System.out.println("hello");
		String id = UUID.randomUUID().toString();
		String message = "hello my first demo";
		sender.sendMsg(id,message);
	}

}
