package com.dianping.cat.demo;

import org.junit.Test;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

public class TestSendMessage {

	@Test
	public void sendMessage() throws Exception {
		for (int i = 0; i < 100; i++) {
			Transaction t = Cat.getProducer().newTransaction("Test", "Test");

			t.addData("key and value");
			t.complete();

		}
		Thread.sleep(1000);
	}

	@Test
	public void sendEvent() throws Exception {
		for (int i = 0; i < 100; i++) {
			Event t = Cat.getProducer().newEvent("Test", "Test");

			t.addData("key and value");
			t.complete();
		}
		Thread.sleep(1000);
	}

	@Test
	public void sendPigeonClientTransaction() throws Exception {
		for (int i = 0; i < 100; i++) {
			Transaction t = Cat.getProducer().newTransaction("PigeonCall", "Method3");
			Cat.getProducer().newEvent("PigeonCall.server", "210.1.6.37");
			t.addData("key and value");

			Thread.sleep(1);
			t.complete();
		}
		for (int i = 0; i < 200; i++) {
			Transaction t = Cat.getProducer().newTransaction("PigeonCall", "Method3");
			Cat.getProducer().newEvent("PigeonCall.server", "110.1.6.48");
			t.addData("key and value");

			Thread.sleep(1);
			t.complete();
		}

		for (int i = 0; i < 300; i++) {
			Transaction t = Cat.getProducer().newTransaction("PigeonCall", "Method3");
			Cat.getProducer().newEvent("PigeonCall.server", "100.1.6.65");
			t.addData("key and value");

			Thread.sleep(1);
			t.complete();
		}
		Thread.sleep(100);
	}
	
	@Test
	public void sendPigeonServerTransaction() throws Exception {
		for (int i = 0; i < 100; i++) {
			Transaction t = Cat.getProducer().newTransaction("PigeonService", "Method6");
			Cat.getProducer().newEvent("PigeonCall.client", "192.168.0.1");
			t.addData("key and value");

			Thread.sleep(1);
			t.complete();
		}
		for (int i = 0; i < 200; i++) {
			Transaction t = Cat.getProducer().newTransaction("PigeonService", "Method8");
			Cat.getProducer().newEvent("PigeonCall.client", "192.168.0.2");
			t.addData("key and value");

			Thread.sleep(1);
			t.complete();
		}

		for (int i = 0; i < 300; i++) {
			Transaction t = Cat.getProducer().newTransaction("PigeonService", "Method5");
			Cat.getProducer().newEvent("PigeonCall.client", "192.168.0.3");
			t.addData("key and value");

			Thread.sleep(1);
			t.complete();
		}
		Thread.sleep(100);
	}
}