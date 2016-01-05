package com.jamp.classloaders;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import view.View;

public class App {

	private static ConfigurableApplicationContext	ctx	= new ClassPathXmlApplicationContext(
			"spring.xml");

	public static void main(String[] args) {
		View view = (View) ctx.getBean("view");
		view.init();
	}
}
