/**
 * Copyright (C) 2018 Luvina Academy
 * LabelCaroActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package model;

/**
 * Class đối tượng người chơi
 * 
 * @author kien vu
 *
 */
public enum Player {
	// Đối tượng người chơi
	// X : Người
	// O : Máy
	X, O;

	/**
	 * Phương thức thực hiện đổi lượt người chơi
	 * 
	 * @param player
	 *            Người chơi hiện tại
	 * @return Người chơi lượt kế tiếp
	 */
	public static Player convertPlayer(Player player) {
		// Nếu người chơi hiện tại là O (Máy)
		if (player == Player.O) {
			// Trả về người chơi là X (Người) để đối lượt chơi
			return Player.X;
		} else {
			// Ngược lại nếu người chơi hiện tại là X (Người) thì trả về người chơi O (Máy)
			return Player.O;
		}
	}

}
