/**
 * Copyright (C) 2018 Luvina Academy
 * Pieces.java 12/11/2018, Vũ Văn Kiên
 */
package model;

/**
 * Class thông tin ô cờ
 */
public class Pieces {

	private int yRow;
	private int xCol;

	/**
	 * @param yRow
	 * @param xCol
	 */
	public Pieces(int yRow, int xCol) {
		super();
		this.yRow = yRow;
		this.xCol = xCol;
	}

	/**
	 * @return the yRow
	 */
	public int getyRow() {
		return yRow;
	}

	/**
	 * @param yRow
	 *            the yRow to set
	 */
	public void setyRow(int yRow) {
		this.yRow = yRow;
	}

	/**
	 * @return the xCol
	 */
	public int getxCol() {
		return xCol;
	}

	/**
	 * @param xCol
	 *            the xCol to set
	 */
	public void setxCol(int xCol) {
		this.xCol = xCol;
	}

}
