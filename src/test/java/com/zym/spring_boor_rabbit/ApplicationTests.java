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

	@Test
	public void myDemo1() {
		String id = UUID.randomUUID().toString();
		String message = "this is fanout message";
		sender.sendFanoutMsg(id,message);
	}

	@Test
	public void myDemo2() {
		String id = UUID.randomUUID().toString();
		String message1 = "this is topic message abcRoutingKey";
		String message2 = "this is topic message defRoutingKey";
		sender.sendTopicMsg1(id,message1);
		sender.sendTopicMsg2(id,message2);

	}


}
