/**
 * Copyright (C) 2018 Luvina Academy
 * LabelCaroActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package logic;

import javax.swing.JLabel;

import model.Pieces;
import utils.Constant;

/**
 * Class dùng để kiểm tra chiến thắng khi chơi game caro
 * 
 * @author kien vu
 *
 */
public class CheckWinGame {

	/**
	 * Phương thức kiểm tra thắng game
	 * 
	 * @param pieces
	 *            ô cờ vừa đánh vào bàn cờ
	 * @param arr
	 *            mảng các ô cờ trên bàn cờ
	 * @param player
	 *            lượt đi của ai X hay O
	 * @return giá trị kiểm tra chiến thắng. 1 - người thắng, 2 - máy thắng, 0 -
	 *         chưa ai thắng
	 */
	public boolean checkWin(Pieces pieces, JLabel[][] arr) {
		// Kết quả trả về khi kiểm tra win theo hàng ngang
		if (checkWinRow(pieces, arr)) {
			// Thắng trả về true
			return true;
		}
		// Kết quả trả về khi kiểm tra win theo hàng dọc
		if (checkWinCol(pieces, arr)) {
			// Thắng trả về true
			return true;
		}
		// Kết quả trả về khi kiểm tra win theo đường chéo trái
		if (checkWinDiagonalLeft(pieces, arr)) {
			// Thắng trả về true
			return true;
		}
		// Kết quả trả về khi kiểm tra win theo đường chéo phải
		if (checkWinDiagonalRight(pieces, arr)) {
			// Thắng trả về true
			return true;
		}
		// Nếu kiểm tra chiến thắng theo cả 4 kiểu mà không có chiến thắng,
		// trả về false
		return false;
	}

	/**
	 * Phương thức kiểm tra chiến thắng theo hàng ngang
	 * 
	 * @param pieces
	 *            nước cờ vừa đánh và cần kiểm tra
	 * @param arr
	 *            mảng JLabel (bàn cờ)
	 * @param symbolPlayer
	 *            ký hiệu đại diện cho loại người chơi. "X" là người. "O" là máy
	 * @return giá trị của kiểm tra chiến thắng. 1 - Người thắng. 2 - Máy thắng.
	 *         3 - Giá trị mặc định (Chưa ai thắng)
	 */
	public boolean checkWinRow(Pieces pieces, JLabel[][] arrLabel) {
		// Lấy giá trị hàng của nước vừa đánh và cần kiểm tra
		int row = pieces.getRow();
		// Lấy giá trị cột của nước vừa đánh và cần kiểm tra
		int col = pieces.getCol();
		// Lấy giá trị của ô cờ cần kiểm tra thắng
		String typePlayer = pieces.getValue();
		// Tạo biến đếm số lượng ô cờ liên tiếp giống nước vừa đi trên hàng
		// ngang có chứa nước cần kiểm tra
		int count = 0;
		// Duyệt kiểm tra và đếm số lượng các quân cờ giống nước vừa đánh trên
		// hàng đi qua nước đó
		for (int index = -4; index <= 4; index++) {
			// Vị trí quân cờ nằm trong bàn cờ
			if (col + index >= 0 && col + index < Constant.COLS) {
				// Giá trị ô cờ bằng giá trị nước vừa đánh
				if (typePlayer.equals(arrLabel[row][col + index].getText())) {
					// Tăng bến đếm quân cờ liên tiếp giống nhau lên 1
					count++;
					// Kiểm tra nếu xuất hiện 5 nước đi giống nhau thì thắng
					if (count == Constant.NUMBER_PIECE_WIN) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		// Trả về giá trị false nếu không có chiến thắng theo đường chéo
		// phải
		return false;
	}

	/**
	 * Phương thức kiểm tra chiến thắng theo cột dọc
	 * 
	 * @param pieces
	 *            nước cờ vừa đánh và cần kiểm tra
	 * @param arr
	 *            mảng JLabel (bàn cờ)
	 * @param symbolPlayer
	 *            ký hiệu đại diện cho loại người chơi. "X" là người. "O" là máy
	 * @return giá trị của kiểm tra chiến thắng. 1 - Người thắng. 2 - Máy thắng.
	 *         3 - Giá trị mặc định (Chưa ai thắng)
	 */
	public boolean checkWinCol(Pieces pieces, JLabel[][] arrLabel) {
		// Lấy giá trị hàng của nước vừa đánh và cần kiểm tra
		int row = pieces.getRow();
		// Lấy giá trị cột của nước vừa đánh và cần kiểm tra
		int col = pieces.getCol();
		// Lấy giá trị của ô cờ cần kiểm tra thắng
		String typePlayer = pieces.getValue();
		// Tạo biến đếm số lượng ô cờ liên tiếp giống nước vừa đi trên cột có
		// chứa nước cần kiểm tra
		int count = 0;
		// Duyệt kiểm tra và đếm số lượng các quân cờ giống nước vừa đánh trên
		// cột đi qua nước đó
		for (int index = -4; index <= 4; index++) {
			// Vị trí quân cờ nằm trong bàn cờ
			if (row + index >= 0 && row + index < Constant.ROWS) {
				// Giá trị ô cờ bằng giá trị nước vừa đánh
				if (typePlayer.equals(arrLabel[row + index][col].getText())) {
					// Tăng bến đếm quân cờ giống nhau liên tiếp lên 1
					count++;
					// Kiểm tra nếu xuất hiện 5 nước đi giống nhau thì thắng
					if (count == Constant.NUMBER_PIECE_WIN) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		// Trả về giá trị false nếu không có chiến thắng theo đường chéo
		// phải
		return false;
	}

	/**
	 * Phương thức kiểm tra chiến thắng theo đường chéo trái
	 * 
	 * @param pieces
	 *            nước cờ vừa đánh và cần kiểm tra
	 * @param arr
	 *            mảng JLabel (bàn cờ)
	 * @param symbolPlayer
	 *            ký hiệu đại diện cho loại người chơi. "X" là người. "O" là máy
	 * @return giá trị của kiểm tra chiến thắng. 1 - Người thắng. 2 - Máy thắng.
	 *         3 - Giá trị mặc định (Chưa ai thắng)
	 */
	public boolean checkWinDiagonalLeft(Pieces pieces, JLabel[][] arrLabel) {
		// Lấy giá trị hàng của nước vừa đánh và cần kiểm tra
		int row = pieces.getRow();
		// Lấy giá trị cột của nước vừa đánh và cần kiểm tra
		int col = pieces.getCol();
		// Lấy giá trị của ô cờ cần kiểm tra thắng
		String typePlayer = pieces.getValue();
		// Tạo biến đếm số lượng ô cờ liên tiếp trên đường chéo có chứa nước cần
		// kiểm tra
		int count = 0;
		// Duyệt kiểm tra và đếm số lượng các quân cờ giống nước vừa đánh trên
		// đường chéo trái đi qua nước đó
		for (int index = -4; index <= 4; index++) {
			// Vị trí quân cờ nằm trong bàn cờ
			if (col + index >= 0 && col + index < Constant.COLS && row + index >= 0 && row + index < Constant.ROWS) {
				// Giá trị ô cờ bằng giá trị nước vừa đánh
				if (typePlayer.equals(arrLabel[row + index][col + index].getText())) {
					// Tăng bến đếm quân cờ giống nhau liên tiếp lên 1
					count++;
					// Kiểm tra nếu xuất hiện từ 5 nước đi giống nhau thì thắng
					if (count == Constant.NUMBER_PIECE_WIN) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		// Trả về giá trị false nếu không có chiến thắng theo đường chéo
		// phải
		return false;
	}

	/**
	 * Phương thức kiểm tra chiến thắng theo đường chéo phải
	 * 
	 * @param pieces
	 *            nước cờ vừa đánh và cần kiểm tra
	 * @param arr
	 *            mảng JLabel (bàn cờ)
	 * @param symbolPlayer
	 *            ký hiệu đại diện cho loại người chơi. "X" là người. "O" là máy
	 * @return giá trị của kiểm tra chiến thắng. 1 - Người thắng. 2 - Máy thắng.
	 *         3 - Giá trị mặc định (Chưa ai thắng)
	 */
	public boolean checkWinDiagonalRight(Pieces pieces, JLabel[][] arrLabel) {
		// Lấy giá trị hàng của nước vừa đánh và cần kiểm tra
		int row = pieces.getRow();
		// Lấy giá trị cột của nước vừa đánh và cần kiểm tra
		int col = pieces.getCol();
		// Lấy giá trị của ô cờ cần kiểm tra thắng
		String typePlayer = pieces.getValue();
		// Tạo biến đếm số lượng ô cờ liên tiếp trên đường chéo có chứa nước cần
		// kiểm tra
		int count = 0;
		// Duyệt kiểm tra và đếm số lượng các quân cờ giống nước vừa đánh trên
		// đường chéo phải đi qua nước đó
		for (int index = -4; index <= 4; index++) {
			// Vị trí quân cờ nằm trong bàn cờ
			if (col - index >= 0 && col - index < Constant.COLS && row + index >= 0 && row + index < Constant.ROWS) {
				// Giá trị ô cờ bằng giá trị nước vừa đánh
				if (typePlayer.equals(arrLabel[row + index][col - index].getText())) {
					// Tăng bến đếm quân cờ giống nhau liên tiếp lên 1
					count++;
					// Kiểm tra nếu xuất hiện từ 5 nước đi giống nhau thì thắng
					if (count == Constant.NUMBER_PIECE_WIN) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		// Trả về giá trị mặc định nếu không có chiến thắng theo đường chéo
		// phải
		return false;
	}

}
