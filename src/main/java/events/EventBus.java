package events;

import java.util.ArrayList;
import java.util.List;

import logic.Observer;

public class EventBus {

	private List<Observer>	observers	= new ArrayList<Observer>();
	private InputEvent	   inputEvent;

	public InputEvent getInputEvent() {
		return inputEvent;
	}

	public void setInputEvent(InputEvent inputEvent) {
		this.inputEvent = inputEvent;
		notifyAllObservers();
	}

	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
}
