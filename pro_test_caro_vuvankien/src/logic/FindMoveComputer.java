/**
 * Copyright (C) 2018 Luvina Academy
 * LabelCaroActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package logic;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

import common.Constant;
import model.Pieces;

/**
 * Class dùng để lấy danh sách thế cờ và tìm nước đi cho máy
 * 
 * @author kien vu
 *
 */
public class FindMoveComputer {

	/**
	 * Phương thức tìm nước đi phù hợp để cho máy tính đi
	 * 
	 * @param arrLabel
	 *            Mảng các ô cờ trên bàn cờ
	 * @param al
	 *            Danh sách các thế cờ 5x5
	 * @return Nước cờ phù hợp để đánh
	 */
	public Pieces getMoveComputer(JLabel[][] arrLabel, ArrayList<String> alFlag) {
		// Duyệt danh sách thế cờ
		for (String flag : alFlag) {
			// Duyệt bàn cờ
			for (int i = 0; i <= Constant.ROWS - Constant.FLAG_MATRIX_SIZE; i++) {
				for (int j = 0; j <= Constant.COLS - Constant.FLAG_MATRIX_SIZE; j++) {
					// Lấy ra chuỗi khi chuyển đổi ma trận con 5x5 trên bàn cờ
					String strMatrix = getStringFromMatrixSub(arrLabel, i, j);
					// Tạo chuỗi so khớp từ chuỗi thế cờ
					// Ghi đè ký hiệu D bằng " "
					// Ghi đè ký hiệu G bằng [X,O, ] nghĩa là có thể là X, O, hoặc " "
					// Ghi đè ký hiệu T bằng " "
					String regex = flag.replaceAll("D", " ").replaceAll("G", "[X,O, ]").replaceAll("T", " ");
					// Nếu 2 chuỗi so khớp được với nhau
					if (strMatrix.matches(regex)) {
						// Lấy vị trí cần đánh trong chuỗi thế cờ đang xét
						int indexMove = flag.indexOf("T");
						// Tính tọa độ nước đi máy cần đánh
						// Vị trí hàng bằng vị trí hàng hiện tại của mảng con
						// trên bàn cờ + vị trí hàng của T trong ma trận thế cờ đang xét
						// Vị trí cột bằng vị trí cột hiện tại của mảng con
						// trên bàn cờ + vị trí cột của T trong ma trận thế cờ đang xét
						int row = i + indexMove / Constant.FLAG_MATRIX_SIZE;
						int col = j + indexMove % Constant.FLAG_MATRIX_SIZE;
						// Tạo đối tượng nước đi
						Pieces pieces = new Pieces("O", row, col);
						// Trả về đối tượng nước đi
						return pieces;
					}
				}
			}
		}
		// Nếu trong file thế cờ không có kết quả nào đạt yêu cầu thì trả về 1
		// đối tượng nước đi ngẫu nhiên
		return randomMove(arrLabel);
	}

	/**
	 * Phương thức chuyển mảng con 5x5 trên bàn cờ về dạng chuỗi
	 * 
	 * @param arrLabel
	 *            Mảng các ô cờ trên bàn cờ
	 * @param row
	 *            vị trí hàng bắt đầu tính để lấy ma trận con 5x5
	 * @param col
	 *            vị trí cột bắt đầu tính để lấy ma trận con 5x5
	 * @return Chuỗi được chuyển đổi từ ma trận con 5x5
	 */
	private String getStringFromMatrixSub(JLabel[][] arrLabel, int row, int col) {
		// Tạo đối tượng StringBuider
		StringBuilder result = new StringBuilder("");
		// Lấy mảng con 5x5 trong bàn cờ tính từ vị trí ô cờ ở hàng row và cột
		// col trong bàn cờ
		for (int i = row; i < row + Constant.FLAG_MATRIX_SIZE; i++) {
			for (int j = col; j < col + Constant.FLAG_MATRIX_SIZE; j++) {
				// Lấy giá trị của ô cờ đó
				String text = arrLabel[i][j].getText();
				// Nếu ô đó chưa đánh (rỗng) thì nối thêm " " để cho chuỗi đủ 25
				// ký tự cho dễ trong việc so khớp với ma trận thế cờ
				if ("".equals(text)) {
					result.append(" ");
					// Nối ký tự đó vào StringBuider
				} else {
					result.append(text);
				}
			}
		}
		// Trả về chuỗi sau khi chuyển đổi mảng con 5x5 trên bàn cờ
		return result.toString();
	}

	/**
	 * Phương thức sinh nước đi ngẫu nhiên có thể đánh trên bàn cờ
	 * 
	 * @param arrLabel
	 *            mảng các ô cờ
	 * @return 1 nước đi ngẫu nhiên có thể đi được trên bàn cờ
	 */
	private Pieces randomMove(JLabel[][] arrLabel) {
		// Tạo 2 biến hàng và cột random
		int rowRandom = 0;
		int colRandom = 0;
		// Tạo đối tượng random
		Random random = new Random();
		// Cho vòng lặp để kiểm tra nước sinh ngẫu nhiên đến khi nào nước đó chưa đánh
		do {
			// Sinh ngẫu nhiên vị trí hàng
			rowRandom = random.nextInt(Constant.ROWS);
			// Sinh ngẫu nhiên vị trí cột
			colRandom = random.nextInt(Constant.COLS);
		} while (!"".equals(arrLabel[rowRandom][colRandom].getText()));
		// Nếu sinh ra nước đi phù hợp (ô cờ trống trên bàn cờ)
		// Trả về nước đi đó
		return new Pieces("O", rowRandom, colRandom);
	}
}
