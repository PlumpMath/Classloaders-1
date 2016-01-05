package events;

import model.ClassLoadData;

/**
 * Holds the input from console.
 *
 * @author Admin
 *
 */
public class InputEvent {

	private ClassLoadData	data;

	public InputEvent() {
	}

	public InputEvent(ClassLoadData data) {
		this.data = data;
	}

	public ClassLoadData getData() {
		return data;
	}

	public void setData(ClassLoadData data) {
		this.data = data;
	}

}
