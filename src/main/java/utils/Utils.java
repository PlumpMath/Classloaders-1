package utils;

public class Utils {

	/**
	 * Получаем каноническое имя класса
	 *
	 * @param className
	 *
	 * @return
	 */

	public static String stripClassName(String className) {
		// delete .class - 6 simbols
		return className.substring(0, className.length() - 6);
	}

	/**
	 * Преобразуем имя в файловой системе в имя класса (заменяем слэши на точки)
	 *
	 * @param className
	 *
	 * @return
	 */

	public static String normalize(String className) {
		return className.replace('/', '.');
	}

}
