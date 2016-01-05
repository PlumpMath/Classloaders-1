package view;

import java.util.Scanner;

import model.ClassLoadData;
import events.EventBus;
import events.InputEvent;

public class View {

	public static final String	PLEASE_ENTER_CLASSPATH	   = "Please, enter the classpath (example, logic/Semaphore.class):";
	public static final String	PLEASE_PACKAGE_NAME	       = "Please, enter the package name:";
	public static final String	PLEASE_CLASS_NAME	       = "Please, enter the class name:";
	public static final String	PLEASE_METHOD_TO_CALL_NAME	= "Please, enter the name of the method to call:";

	public static final String	EXIT_COMMAND	           = "exit";
	public static final String	HELP_MESSAGE	           = "To exit the program, please, type \""
			+ EXIT_COMMAND
			+ "\".";
	public static final String	DONE	                   = "Done.";

	private EventBus	       eventBus;

	public void init() {

		System.out.println(HELP_MESSAGE);
		Scanner scanner = new Scanner(System.in);
		ClassLoadData classLoadData = new ClassLoadData();

		while (true) {

			System.out.println(PLEASE_ENTER_CLASSPATH);

			// Classpath
			String input = scanner.nextLine();
			if (input.equals(EXIT_COMMAND)) {
				break;
			} else {
				classLoadData.setFilePath(input);
			}

			// Package
			System.out.println(PLEASE_PACKAGE_NAME);

			input = scanner.nextLine();
			if (input.equals(EXIT_COMMAND)) {
				break;
			} else {
				classLoadData.setPackageName(input);
			}

			// Class name
			System.out.println(PLEASE_CLASS_NAME);

			input = scanner.nextLine();
			if (input.equals(EXIT_COMMAND)) {
				break;
			} else {
				classLoadData.setClassName(input);
			}

			// Method to call
			System.out.println(PLEASE_METHOD_TO_CALL_NAME);

			input = scanner.nextLine();
			if (input.equals(EXIT_COMMAND)) {
				break;
			} else {
				classLoadData.setMethodToCall(input);
			}

			// Send to the observer
			eventBus.setInputEvent(new InputEvent(classLoadData));
		}
		scanner.close();
		System.out.println(DONE);
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}
}
