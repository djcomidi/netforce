package ui;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.IEngine;

public class CurrentPositionSpinner extends JSpinner implements ChangeListener {
	private final IEngine engine;
	private final int rotorId;

	public CurrentPositionSpinner(IEngine engine, int rotorId) {
		super();
		this.engine = engine;
		this.rotorId = rotorId;
		this.setModel(new CurrentPositionModel());
		this.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		engine.setCurrentPosition(rotorId, (int) getValue());
	}

	private class CurrentPositionModel extends AbstractSpinnerModel {
		private int currentPosition;

		public CurrentPositionModel() {
			currentPosition = 0;
		}

		@Override
		public Object getNextValue() {
			return (currentPosition + 1) % 26;
		}

		@Override
		public Object getPreviousValue() {
			return (currentPosition + 25) % 26;
		}

		@Override
		public Object getValue() {
			return currentPosition;
		}

		@Override
		public void setValue(Object value) {
			currentPosition = (int) value;
			fireStateChanged();
		}

	}
}
