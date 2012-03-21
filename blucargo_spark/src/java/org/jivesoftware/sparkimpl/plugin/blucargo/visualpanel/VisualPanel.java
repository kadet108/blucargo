package org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel;

import javax.swing.JPanel;

import com.blucargo.model.CargoOffer;

public abstract class VisualPanel extends JPanel {

	private static final long serialVersionUID = -9157281634266535026L;
	private CargoOffer cargoOffer;
	private int row;
	private int column;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public VisualPanel() {

	}

	public VisualPanel(CargoOffer cargoOffer) {
		this.cargoOffer = cargoOffer;
	}

	public CargoOffer getCargoOffer() {
		return cargoOffer;
	}

	public void setCargoOffer(CargoOffer cargoOffer) {
		this.cargoOffer = cargoOffer;
	}

	public abstract void render(CargoOffer cargoOffer, JPanel panel, int row);
}
