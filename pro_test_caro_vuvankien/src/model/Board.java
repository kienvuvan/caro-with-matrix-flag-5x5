/**
 * Copyright (C) 2018 Luvina Academy
 * CaroView.java 12/11/2018, Vũ Văn Kiên
 */
package model;

import javax.swing.JLabel;

/**
 * Class bàn cờ caro
 * 
 * @author kien vu
 *
 */
public class Board {
	private JLabel[][] arrLabel;

	/**
	 * @param arrLabel
	 */
	public Board(JLabel[][] arrLabel) {
		super();
		this.arrLabel = arrLabel;
	}

	/**
	 * @return the arrLabel
	 */
	public JLabel[][] getArrLabel() {
		return arrLabel;
	}

	/**
	 * @param arrLabel
	 *            the arrLabel to set
	 */
	public void setArrLabel(JLabel[][] arrLabel) {
		this.arrLabel = arrLabel;
	}
}
