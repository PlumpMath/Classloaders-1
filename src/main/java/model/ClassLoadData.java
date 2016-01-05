package model;

/**
 * DTO for class load data.
 *
 * @author Admin
 *
 */

public class ClassLoadData {

	private String	filePath;
	private String	packageName;
	private String	methodToCall;
	private String	className;

	public ClassLoadData() {
	}

	public ClassLoadData(String filePath, String packageName,
			String methodToCall, String className) {

		this.filePath = filePath;
		this.packageName = packageName;
		this.methodToCall = methodToCall;
		this.className = className;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getMethodToCall() {
		return methodToCall;
	}

	public void setMethodToCall(String methodToCall) {
		this.methodToCall = methodToCall;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
