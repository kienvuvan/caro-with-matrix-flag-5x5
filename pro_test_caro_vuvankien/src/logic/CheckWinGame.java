/**
 * Copyright (C) 2018 Luvina Academy
 * LabelCaroActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package logic;

import javax.swing.JLabel;
import common.Constant;
import model.Pieces;

/**
 * Class dùng để kiểm tra chiến thắng khi chơi game caro
 * 
 * @author kien vu
 *
 */
public class CheckWinGame {

	// Khai báo biến đối tượng bàn cờ cần kiểm tra
	private JLabel[][] arrLabel;

	/**
	 * Phương thức khởi tạo đối tượng CheckWinGame với tham số truyền vào là
	 * mảng bàn cờ
	 */
	public CheckWinGame(JLabel[][] arrLabel) {
		this.arrLabel = arrLabel;
	}

	/**
	 * Phương thức kiểm tra thắng game
	 * 
	 * @param pieces
	 *            ô cờ vừa đánh vào bàn cờ
	 * @return giá trị kiểm tra chiến thắng. 1 - người thắng, 2 - máy thắng, 0 -
	 *         chưa ai thắng
	 */
	public boolean checkWin(Pieces pieces) {
		// Lấy giá trị hàng của nước vừa đánh và cần kiểm tra
		int row = pieces.getRow();
		// Lấy giá trị cột của nước vừa đánh và cần kiểm tra
		int col = pieces.getCol();
		// Lấy giá trị của ô cờ cần kiểm tra thắng
		String typePlayer = pieces.getValue();
		// Kết quả trả về khi kiểm tra win
		if (checkWinRow(row, col, typePlayer) || checkWinCol(row, col, typePlayer)
				|| checkWinDiagonalLeft(row, col, typePlayer) || checkWinDiagonalRight(row, col, typePlayer)) {
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
	 * @param row
	 *            vị trí hàng của nước cần kiểm tra
	 * @param col
	 *            vị trí cột của nước cần kiểm tra
	 * @param typePlayer
	 *            người chơi (X hay O) nước cờ vừa đánh và cần kiểm tra
	 * @return giá trị của kiểm tra chiến thắng. 1 - Người thắng. 2 - Máy thắng.
	 *         3 - Giá trị mặc định (Chưa ai thắng)
	 */
	private boolean checkWinRow(int row, int col, String typePlayer) {
		// Tạo biến đếm số lượng ô cờ liên tiếp giống nước vừa đi trên hàng
		// ngang có chứa nước cần kiểm tra
		int count = 0;
		// Duyệt kiểm tra và đếm số lượng các quân cờ giống nước vừa đánh trên
		// hàng đi qua nước đó
		for (int index = -Constant.NUMBER_PIECE_WIN + 1; index <= Constant.NUMBER_PIECE_WIN - 1; index++) {
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
	 * @param row
	 *            vị trí hàng của nước cần kiểm tra
	 * @param col
	 *            vị trí cột của nước cần kiểm tra
	 * @param typePlayer
	 *            người chơi (X hay O) nước cờ vừa đánh và cần kiểm tra
	 * @return giá trị của kiểm tra chiến thắng. 1 - Người thắng. 2 - Máy thắng.
	 *         3 - Giá trị mặc định (Chưa ai thắng)
	 */
	private boolean checkWinCol(int row, int col, String typePlayer) {
		// Tạo biến đếm số lượng ô cờ liên tiếp giống nước vừa đi trên cột có
		// chứa nước cần kiểm tra
		int count = 0;
		// Duyệt kiểm tra và đếm số lượng các quân cờ giống nước vừa đánh trên
		// cột đi qua nước đó
		for (int index = -Constant.NUMBER_PIECE_WIN + 1; index <= Constant.NUMBER_PIECE_WIN - 1; index++) {
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
	 * @param row
	 *            vị trí hàng của nước cần kiểm tra
	 * @param col
	 *            vị trí cột của nước cần kiểm tra
	 * @param typePlayer
	 *            người chơi (X hay O) nước cờ vừa đánh và cần kiểm tra
	 * @return giá trị của kiểm tra chiến thắng. 1 - Người thắng. 2 - Máy thắng.
	 *         3 - Giá trị mặc định (Chưa ai thắng)
	 */
	private boolean checkWinDiagonalLeft(int row, int col, String typePlayer) {
		// Tạo biến đếm số lượng ô cờ liên tiếp trên đường chéo có chứa nước cần
		// kiểm tra
		int count = 0;
		// Duyệt kiểm tra và đếm số lượng các quân cờ giống nước vừa đánh trên
		// đường chéo trái đi qua nước đó
		for (int index = -Constant.NUMBER_PIECE_WIN + 1; index <= Constant.NUMBER_PIECE_WIN - 1; index++) {
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
	 * @param row
	 *            vị trí hàng của nước cần kiểm tra
	 * @param col
	 *            vị trí cột của nước cần kiểm tra
	 * @param typePlayer
	 *            người chơi (X hay O) nước cờ vừa đánh và cần kiểm tra
	 * @return giá trị của kiểm tra chiến thắng. 1 - Người thắng. 2 - Máy thắng.
	 *         3 - Giá trị mặc định (Chưa ai thắng)
	 */
	private boolean checkWinDiagonalRight(int row, int col, String typePlayer) {
		// Tạo biến đếm số lượng ô cờ liên tiếp trên đường chéo có chứa nước cần
		// kiểm tra
		int count = 0;
		// Duyệt kiểm tra và đếm số lượng các quân cờ giống nước vừa đánh trên
		// đường chéo phải đi qua nước đó
		for (int index = -Constant.NUMBER_PIECE_WIN + 1; index <= Constant.NUMBER_PIECE_WIN - 1; index++) {
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
