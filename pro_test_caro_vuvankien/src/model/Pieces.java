/**
 * Copyright (C) 2018 Luvina Academy
 * Pieces.java 12/11/2018, Vũ Văn Kiên
 */
package model;

/**
 * Class thông tin ô cờ
 */
public class Pieces {

	private String value;
	private int row;
	private int col;

	/**
	 * Phương thức khởi tạo ô cờ với 3 tham số truyền vào
	 * 
	 * @param value
	 *            giá trị của ô cờ (X,O,"")
	 * @param row
	 *            vị trí hàng của ô cờ
	 * @param col
	 *            vị trí cột của ô cờ
	 */
	public Pieces(String value, int row, int col) {
		super();
		this.value = value;
		this.row = row;
		this.col = col;
	}

	/**
	 * Phương thức khởi tạo với 2 tham số truyền vào
	 * 
	 * @param row
	 *            vị trí hàng của ô cờ
	 * @param col
	 *            vị trí cột của ô cờ
	 */
	public Pieces(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	/**
	 * Phương thức lấy giá trị của ô cờ
	 * 
	 * @return the value giá trị của ô cờ
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Phương thức gán giá trị của ô cờ bằng tham số truyền vào
	 * 
	 * @param value
	 *            giá trị ô cờ muốn gán
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Phương thức lấy vị trí hàng của ô cờ
	 * 
	 * @return the row giá trị hàng của ô cờ
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Phương thức gán giá trị vị trí hàng cho ô cờ bằng tham số truyền vào
	 * 
	 * @param row
	 *            giá trị vị trí hàng của ô cờ muốn gán
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Phương thức lấy vị trí cột của ô cờ
	 * 
	 * @return the col giá trị cột của ô cờ
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Phương thức gán giá trị vị trí cột cho ô cờ bằng tham số truyền vào
	 * 
	 * @param col
	 *            giá trị vị trí cột của ô cờ muốn gán
	 */
	public void setCol(int col) {
		this.col = col;
	}

}
