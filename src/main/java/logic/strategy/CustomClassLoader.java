package logic.strategy;

import java.util.HashMap;

public class CustomClassLoader extends ClassLoader {
	protected static String	            WARNING	= "Warning : No jar file found. Packet unmarshalling won't be possible. "
	                                                    + "Please verify your classpath";

	protected HashMap<String, Class<?>>	cache	= new HashMap<String, Class<?>>();
	protected String	                fileName;
	protected String	                packageName;

	/**
	 * Собственно метод, который и реализует загрузку класса
	 */

	@Override
	public synchronized Class<?> loadClass(String name)
	        throws ClassNotFoundException {
		Class<?> result = cache.get(name);

		// Возможно класс вызывается не по полному имени - добавим имя пакета
		if (result == null) {
			result = cache.get(packageName + "." + name);
		}

		// Если класса нет в кэше то возможно он системный

		if (result == null) {
			result = super.findSystemClass(name);
		}
		System.out.println("== loadClass(" + name + ")");
		return result;
	}

	/**
	 *
	 * Валидация класса - проверят принадлежит ли класс заданному пакету и имеет
	 * ли он расширение .class
	 *
	 * @param className
	 *
	 * @param packageName
	 *
	 * @return
	 */

	protected boolean match(String className, String packageName) {
		return className.startsWith(packageName)
				&& className.endsWith(".class");
	}
}
