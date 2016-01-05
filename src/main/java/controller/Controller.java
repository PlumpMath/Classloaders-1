package controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import logic.Observer;
import logic.strategy.FileClassLoader;
import logic.strategy.JarClassLoader;
import model.ClassLoadData;
import utils.Utils;
import view.View;
import events.EventBus;
import events.InputEvent;

public class Controller implements Observer {

	private static final String	IMPOSSIBLE_TO_LOAD_A_CLASS	= "Impossible to load a class";

	private View	            view;
	private EventBus	        eventBus;
	private ClassLoader	        classLoader;

	public Controller(View view, EventBus eventBus) {
		this.view = view;
		this.eventBus = eventBus;
	}

	public void update() {
		InputEvent event = eventBus.getInputEvent();
		if (event != null) {
			loadClass(event.getData());
		}
	}

	private void loadClass(ClassLoadData data) {

		String className;

		if (data.getFilePath().endsWith(".jar")) {
			classLoader = new JarClassLoader(data.getFilePath(),
					data.getPackageName());

			className = data.getClassName();

		} else if (data.getFilePath().endsWith(".class")) {
			classLoader = new FileClassLoader(data.getFilePath(),
					data.getPackageName());

			className = Utils
			        .stripClassName(Utils.normalize(data.getFilePath()));
		} else {
			System.out.println(IMPOSSIBLE_TO_LOAD_A_CLASS);
			return;
		}

		try {
			Object obj = classLoader.loadClass(className).newInstance();

			Class<?> clazz = obj.getClass();
			Method method = clazz.getDeclaredMethod(data.getMethodToCall());
			method.invoke(obj);

		} catch (ClassNotFoundException e) {
			System.out.println("An exeption has been thrown");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}
}
