package logic.strategy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import utils.Utils;

/**
 * Загружаем файлы из заданного jar-архива Классы должны относится к заданному
 * пакету - пример валидации при загрузке
 *
 */

public class JarClassLoader extends CustomClassLoader {

	public JarClassLoader(String jarFileName, String packageName) {
		this.fileName = jarFileName;
		this.packageName = packageName;
		cacheClasses();
	}

	/**
	 * При создании загрузчика извлекаем все классы из jar и кэшируем в памяти
	 *
	 */
	private void cacheClasses() {

		try {
			JarFile jarFile = new JarFile(fileName);
			Enumeration<JarEntry> entries = jarFile.entries();

			while (entries.hasMoreElements()) {

				JarEntry jarEntry = entries.nextElement();

				// Одно из назначений хорошего загрузчика - валидация классов на
				// этапе загрузки

				if (match(Utils.normalize(jarEntry.getName()), packageName)) {

					byte[] classData = loadClassData(jarFile, jarEntry);

					if (classData != null) {

						// Create a class from bytecode
						Class<?> clazz = defineClass(Utils.stripClassName(Utils
						        .normalize(jarEntry.getName())), classData, 0,
						        classData.length);

						cache.put(clazz.getName(), clazz);

						System.out.println("== class " + clazz.getName()
								+ " loaded in cache");
					}
				}
			}
		} catch (IOException IOE) {
			// Просто выведем сообщение об ошибке
			System.out.println(WARNING);
		}
	}

	/**
	 * Извлекаем файл из заданного JarEntry
	 *
	 * @param jarFile
	 *            - файл jar-архива из которого извлекаем нужный файл
	 *
	 * @param jarEntry
	 *            - jar-сущность которую извлекаем
	 *
	 * @return null если невозможно прочесть файл
	 */

	private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry)
	        throws IOException {

		long size = jarEntry.getSize();
		if (size == -1 || size == 0)
			return null;

		byte[] data = new byte[(int) size];
		InputStream in = jarFile.getInputStream(jarEntry);
		in.read(data);
		return data;
	}
}
