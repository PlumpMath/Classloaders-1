package logic.strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import utils.Utils;

public class FileClassLoader extends CustomClassLoader {

	public FileClassLoader(String fileName, String packageName) {

		this.fileName = fileName;
		this.packageName = packageName;
		cacheClasses();
	}

	private void cacheClasses() {

		try {
			// fileName = "logic/Semaphore.class";

			File file = new File(fileName);

			// Одно из назначений хорошего загрузчика - валидация классов на
			// этапе загрузки

			if (match(Utils.normalize(file.getPath()), packageName)) {

				byte[] classData = loadClassData(file);

				if (classData != null) {

					// Create a class from bytecode
					Class<?> clazz = defineClass(
							Utils.stripClassName(Utils.normalize(fileName)),
							classData, 0, classData.length);

					this.cache.put(clazz.getName(), clazz);

					System.out.println("== class " + clazz.getName()
							+ " loaded in cache");
				}
			}
		} catch (IOException IOE) {
			System.out.println(WARNING);
		}
	}

	/**
	 * Converts a file into byte array.
	 *
	 * @param file
	 *            - file to convert
	 * @return a byte array
	 * @throws IOException
	 *             is thrown if something is whrong with a file convertion
	 *
	 */
	private byte[] loadClassData(File file) throws IOException {

		FileInputStream fileInputStream = null;

		byte[] bFile = new byte[(int) file.length()];

		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}
}
