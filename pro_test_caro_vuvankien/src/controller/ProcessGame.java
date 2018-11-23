/**
 * Copyright (C) 2018 Luvina Academy
 * ProcessGame.java 12/11/2018, Vũ Văn Kiên
 */
package controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Pieces;
import model.Player;
import utils.Constant;
import view.CaroView;

/**
 * Class thực hiện xử lý trong game
 * 
 * @author kien vu
 *
 */
public class ProcessGame {

	private CaroView caroView;
	private JLabel[][] arrLabel;
	private ArrayList<String> alFlagChess;
	private Player player;

	/**
	 * Khởi tạo đối tượng ProcessGame với tham số CaroView được truyền vào
	 * 
	 * @param caroView
	 *            đối tượng CaroView
	 */
	public ProcessGame(CaroView caroView) {
		super();
		this.caroView = caroView;
		arrLabel = caroView.getArrLabel();
		alFlagChess = caroView.getAlFlagChess();
		player = caroView.getPlayer();
	}

	/**
	 * Phương thức lấy danh sách tất cả các thế cờ ma trận 5x5 trong file thế cờ
	 * 
	 * @return ArrayList<String> danh sách tất cả các thế cờ
	 * @throws Exception
	 *             Không tìm được file hoặc lỗi trong quá trình đọc file
	 */
	public ArrayList<String> getAllMove() throws Exception {
		// Tạo list<String>
		ArrayList<String> al = new ArrayList<>();
		// Tạo đối tượng file có đường dẫn đến file ma trận thế cờ
		File fileMaxtrix = new File("src/res/matrix.txt");
		// Tạo luồng đọc file thế cờ
		DataInputStream dis = new DataInputStream(new FileInputStream(fileMaxtrix));
		String out;
		// Tạo đối tượng StringBuider
		StringBuilder result = new StringBuilder("");
		// Tạo biến đếm
		int count = 0;
		// Đọc toàn bộ file
		while ((out = dis.readLine()) != null) {
			// Nối dòng đọc được vào StringBuider và loại bỏ dấu cách trong
			// chuỗi đọc được
			result.append(out.replaceAll(" ", ""));
			// Tăng biến đếm dòng lên 1
			count++;
			// Nếu biến đếm chia hết cho 6 (tính cả dòng cách ra) nghĩa là có 1
			// ma trận thế cờ 5x5
			if (count % 6 == 0) {
				// Thêm vào danh sách
				al.add(result.toString());
				// Reset chuỗi trong StringBuider về 0
				result.delete(0, result.length());
			}
		}
		// Đọc xong thì đóng luồng đọc file lại
		dis.close();
		// Trả về danh sách thế cờ
		return al;
	}

	/**
	 * Phương thức tìm nước đi phù hợp để cho máy tính đi
	 * 
	 * @param arrLabel
	 *            Mảng các ô cờ trên bàn cờ
	 * @param al
	 *            Danh sách các thế cờ 5x5
	 * @return Nước cờ phù hợp để đánh
	 */
	public Pieces getMoveComputer(JLabel[][] arrLabel, ArrayList<String> al) {
		// Duyệt danh sách thế cờ
		for (String flag : al) {
			// Duyệt bàn cờ
			for (int i = 0; i < Constant.ROWS - 5; i++) {
				for (int j = 0; j < Constant.COLS - 5; j++) {
					// Lấy ra chuỗi khi chuyển đổi ma trận con 5x5 trên bàn cờ
					String strMatrix = getStringFromMatrixSub(arrLabel, i, j);
					// Tạo chuỗi so khớp từ chuỗi thế cờ
					// Ghi đè ký hiệu D bằng " "
					// Ghi đè ký hiệu G bằng [X,O, ] nghĩa là có thể là X, O,
					// hoặc " "
					// Ghi đè ký hiệu T bằng " "
					String regex = flag.replaceAll("D", " ").replaceAll("G", "[X,O, ]").replaceAll("T", " ");
					// Nếu 2 chuỗi so khớp được với nhau
					if (strMatrix.matches(regex)) {
						// Lấy vị trí cần đánh trong chuỗi thế cờ đang xét
						int indexMove = flag.indexOf("T");
						// Tính tọa độ nước đi máy cần đánh
						// Vị trí hàng bằng vị trí hàng hiện tại của mảng con
						// trên bàn cờ + vị trí hàng của T trong ma trận thế cờ
						// đang xét
						// Vị trí cột bằng vị trí cột hiện tại của mảng con
						// trên bàn cờ + vị trí cột của T trong ma trận thế cờ
						// đang xét
						int row = i + indexMove / 5;
						int col = j + indexMove % 5;
						// Tạo đối tượng nước đi
						Pieces pieces = new Pieces(row, col);
						// Trả về đối tượng nước đi
						return pieces;
					}
				}
			}
		}
		// Nếu trong file thế cờ không có kết quả nào đạt yêu cầu thì trả về 1
		// đối tượng nước đi ngẫu nhiên
		Random rd = new Random();
		return new Pieces(rd.nextInt(Constant.ROWS - 1), rd.nextInt(Constant.COLS - 1));
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
	public String getStringFromMatrixSub(JLabel[][] arrLabel, int row, int col) {
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
	public int checkWin(Pieces pieces, JLabel[][] arr, Player player) {
		String symbolPlayer = "";
		// Là người sẽ là "X"
		if (player == Player.X) {
			symbolPlayer = "X";
			// Là máy sẽ là "X"
		} else {
			symbolPlayer = "O";
		}
		// Kết quả trả về khi kiểm tra win theo hàng ngang
		int winRow = checkWinRow(pieces, arr, symbolPlayer);
		// Nếu giá trị này khác giá trị mặc định tức là có chiến thắng
		if (winRow != Constant.DEFAULT) {
			// Trả về giá trị chiến thắng đó
			return winRow;
		}
		// Kết quả trả về khi kiểm tra win theo hàng dọc
		int winCol = checkWinCol(pieces, arr, symbolPlayer);
		// Nếu giá trị này khác giá trị mặc định tức là có chiến thắng
		if (winCol != Constant.DEFAULT) {
			// Trả về giá trị chiến thắng đó
			return winCol;
		}
		// Kết quả trả về khi kiểm tra win theo đường chéo trái
		int winDiagonalLeft = checkWinDiagonalLeft(pieces, arr, symbolPlayer);
		// Nếu giá trị này khác giá trị mặc định tức là có chiến thắng
		if (winDiagonalLeft != Constant.DEFAULT) {
			// Trả về giá trị chiến thắng đó
			return winDiagonalLeft;
		}
		// Kết quả trả về khi kiểm tra win theo đường chéo phải
		int winDiagonalRight = checkWinDiagonalRight(pieces, arr, symbolPlayer);
		// Nếu giá trị này khác giá trị mặc định tức là có chiến thắng
		if (winDiagonalRight != Constant.DEFAULT) {
			// Trả về giá trị chiến thắng đó
			return winDiagonalRight;
		}
		// Nếu kiểm tra chiến thắng theo cả 4 kiểu mà không có chiến thắng, trả
		// về giá trị mặc định
		return Constant.DEFAULT;
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
	public int checkWinRow(Pieces pieces, JLabel[][] arr, String symbolPlayer) {
		// Lấy giá trị hàng của nước vừa đánh và cần kiểm tra
		int row = pieces.getyRow();
		// Biến chạy khi duyệt cột của bàn cờ
		int indexCol = 0;
		// Tạo biến đếm
		int count;
		// Biến kiểm tra thắng
		boolean checkwin;
		// Duyệt cột từ vị trí 0 đến vị trí độ dài bàn cờ - 5
		while (indexCol < Constant.COLS - 5) {
			// Gán biến kiểm tra thắng bằng true
			checkwin = true;
			// Kiểm tra trên 1 hàng nếu tồn tại 5 con giống nhau liên tiếp thì
			// thắng
			for (count = 0; count < 5; count++) {
				// Lấy giá trị của ô cờ đang xét
				String typePlayer = arr[row][indexCol + count].getText();
				// Nếu tồn tại giá trị khác với giá trị tương ứng của người chơi
				if (!symbolPlayer.equalsIgnoreCase(typePlayer)) {
					// Gán bằng false tức là không thắng theo hàng ngang tính từ
					// vị trí đang duyệt
					checkwin = false;
				}
			}
			// Nếu thắng theo hàng tính từ vị trí đang xét
			if (checkwin) {
				// Kiểm tra đối tượng chơi để trả về kết quả tương ứng
				// Constant.HUMAN_WIN : Người win
				// Constant.COMPUTER_WIN : Máy win
				switch (symbolPlayer) {
				// Người win
				case "X":
					return Constant.HUMAN_WIN;
				// Máy win
				case "O":
					return Constant.COMPUTER_WIN;
				}
			}
			// Tăng biến đếm cột lên 1 để tiếp tục vòng while
			indexCol++;
		}
		// Trả về giá trị mặc định nếu không có chiến thắng theo hàng ngang
		return Constant.DEFAULT;
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
	public int checkWinCol(Pieces pieces, JLabel[][] arr, String symbolPlayer) {
		// Lấy giá trị cột của nước vừa đánh và cần kiểm tra
		int col = pieces.getxCol();
		// Biến chạy khi duyệt hàng của bàn cờ
		int indexRow = 0;
		// Tạo biến đếm
		int count;
		// Biến kiểm tra thắng
		boolean checkwin;
		// Duyệt hàng từ vị trí 0 đến vị trí độ rộng bàn cờ - 5
		while (indexRow < Constant.ROWS - 5) {
			// Gán biến kiểm tra thắng bằng true
			checkwin = true;
			// Kiểm tra trên 1 hàng nếu tồn tại 5 con giống nhau liên tiếp thì
			// thắng
			for (count = 0; count < 5; count++) {
				// Lấy giá trị của ô cờ đang xét
				String typePlayer = arr[indexRow + count][col].getText();
				// Nếu tồn tại giá trị khác với giá trị tương ứng của người chơi
				if (!symbolPlayer.equalsIgnoreCase(typePlayer)) {
					// Gán bằng false tức là không thắng theo hàng ngang tính từ
					// vị trí đang duyệt
					checkwin = false;
				}
			}
			// Nếu thắng theo cột tính từ vị trí đang xét
			if (checkwin) {
				// Kiểm tra đối tượng chơi để trả về kết quả tương ứng
				// Constant.HUMAN_WIN : Người win
				// Constant.COMPUTER_WIN : Máy win
				switch (symbolPlayer) {
				// Người win
				case "X":
					return Constant.HUMAN_WIN;
				// Máy win
				case "O":
					return Constant.COMPUTER_WIN;
				}
			}
			// Tăng biến đếm hàng lên 1 để tiếp tục vòng while
			indexRow++;
		}
		// Trả về giá trị mặc định nếu không có chiến thắng cột dọc
		return Constant.DEFAULT;
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
	public int checkWinDiagonalLeft(Pieces pieces, JLabel[][] arr, String symbolPlayer) {
		// Lấy giá trị hàng của nước vừa đánh và cần kiểm tra
		int row = pieces.getyRow();
		// Lấy giá trị cột của nước vừa đánh và cần kiểm tra
		int col = pieces.getxCol();
		// Tạo biến đếm
		int count;
		// Biến kiểm tra thắng
		boolean checkwin;
		// Gán biến chạy theo hàng bằng vị trí hàng của ô vừa đánh
		int indexRow = row;
		// Gán biến chạy theo cột bằng vị trí cột của ô vừa đánh
		int indexCol = col;
		// Dùng vòng lặp while để tìm trả về vị trí trên cùng bàn cờ theo đường
		// chéo xuôi đi qua điểm vừa đánh
		while (indexRow > 0 && indexCol > 0) {
			// Giảm biến đếm hàng đi 1
			indexRow--;
			// Giảm biến đếm cột đi 1
			indexCol--;
		}
		// Duyệt đường chéo xuôi đi qua 2 ô (1 ô đánh, 1 ô vừa tìm bên trên)
		while (indexRow <= Constant.ROWS - 5 && indexCol <= Constant.COLS - 5) {
			// // Gán biến kiểm tra thắng bằng true
			checkwin = true;
			// Kiểm tra trên 1 đường chéo đó nếu tồn tại 5 con giống nhau liên
			// tiếp thì
			// thắng
			for (count = 0; count < 5; count++) {
				// Lấy giá trị của ô cờ đang duyệt
				String typePlayer = arr[indexRow + count][indexCol + count].getText();
				// Nếu tồn tại giá trị khác với giá trị tương ứng của người chơi
				if (!symbolPlayer.equalsIgnoreCase(typePlayer)) {
					// Gán bằng false tức là không thắng theo đường chéo trái
					// tính từ
					// vị trí đang xét
					checkwin = false;
				}
			}
			// Nếu thắng theo đường chéo tính từ vị trí đang xét
			if (checkwin) {
				switch (symbolPlayer) {
				// Người win
				case "X":
					return Constant.HUMAN_WIN;
				// Máy win
				case "O":
					return Constant.COMPUTER_WIN;
				}
			}
			// Tăng biến chạy hàng lên 1 để tiếp tục vòng while
			indexRow++;
			// Tăng biến chạy cột lên 1 để tiếp tục vòng while
			indexCol++;
		}
		// Trả về giá trị mặc định nếu không có chiến thắng theo đường chéo trái
		return Constant.DEFAULT;
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
	public int checkWinDiagonalRight(Pieces pieces, JLabel[][] arr, String symbolPlayer) {
		// Lấy giá trị hàng của nước vừa đánh và cần kiểm tra
		int row = pieces.getyRow();
		// Lấy giá trị cột của nước vừa đánh và cần kiểm tra
		int col = pieces.getxCol();
		// Biến chạy khi duyệt hàng của bàn cờ
		int indexRow = row;
		// Biến chạy khi duyệt cột của bàn cờ
		int indexCol = col;
		// Tạo biến đếm
		int count;
		// Biến kiểm tra thắng
		boolean checkwin;
		// Dùng vòng lặp while để tìm được vị trí dưới cùng bên trái nằm trên
		// đường chéo đi qua điểm cần kiểm tra
		while (indexRow < Constant.ROWS - 1 && indexCol > 0) {
			// Tăng biến đếm hàng lên 1
			indexRow++;
			// Giảm biến đếm cột đi 1
			indexCol--;
		}
		// Duyệt vòng lặp while để kiểm tra trên đường chéo mà vừa tìm được xem
		// có tồn tại chiến thắng không
		while (indexRow >= 4 && indexCol <= Constant.COLS - 5) {
			// Gán biến kiểm tra thắng = true
			checkwin = true;
			// Kiểm tra trên 1 đường chéo đó nếu tồn tại 5 con giống nhau liên
			// tiếp thì
			// thắng
			for (count = 0; count < 5; count++) {
				// Lấy giá trị của ô cờ đang duyệt
				String typePlayer = arr[indexRow - count][indexCol + count].getText();
				// Nếu tồn tại giá trị khác với giá trị tương ứng của người chơi
				if (!symbolPlayer.equalsIgnoreCase(typePlayer)) {
					// Gán bằng false tức là không thắng theo đường chéo phải
					// tính từ
					// vị trí đang xét
					checkwin = false;
				}
			}
			// Nếu thắng theo đường chéo tính từ vị trí đang xét
			if (checkwin) {
				switch (symbolPlayer) {
				// Người win
				case "X":
					return Constant.HUMAN_WIN;
				// Máy win
				case "O":
					return Constant.COMPUTER_WIN;
				}
			}
			// Giảm biếm đếm hàng đi 1
			indexRow--;
			// Tăng biến đếm cột lên 1
			indexCol++;
		}
		// Trả về giá trị mặc định nếu không có chiến thắng theo đường chéo phải
		return Constant.DEFAULT;
	}

	/**
	 * Phương thức tạo game mới
	 */
	public boolean newGame() {
		// Hỏi người chơi ai đánh trước
		int input = JOptionPane.showConfirmDialog(null, "Bạn đi trước");
		if (input != JOptionPane.CANCEL_OPTION) {
			// Set tình trạng game về true để tiến hành chơi
			caroView.setPlayGame(true);
			// Reset bàn cờ
			resetBoard(arrLabel);
			if (input == JOptionPane.NO_OPTION) {
				// Đánh 1 nước chính sữa bàn cờ
				arrLabel[Constant.ROWS / 2][Constant.COLS / 2].setText("O");
				// Đổi lượt cho người chơi
				caroView.setPlayer(Player.X);
				// Tăng biến đếm nước đi lên 1
				caroView.setCount(caroView.getCount() + 1);
			}
			// Người đánh
			caroView.setPlayer(Player.X);
			return true;
		}
		return false;
	}

	/**
	 * Phương thức bắt đầu game
	 */
	public boolean startGame() {
		// Hỏi người chơi ai đánh trước
		int input = JOptionPane.showConfirmDialog(null, "Bạn đi trước");
		// Máy đánh trước
		if (input != JOptionPane.CANCEL_OPTION) {
			// Set tình trạng game về true để tiến hành chơi
			caroView.setPlayGame(true);
			if (input == JOptionPane.NO_OPTION) {
				// Đánh 1 nước chính sữa bàn cờ
				arrLabel[Constant.ROWS / 2][Constant.COLS / 2].setText("O");
				// Đổi lượt cho người chơi
				caroView.setPlayer(Player.X);
				// Tăng biến đếm nước đi lên 1
				caroView.setCount(caroView.getCount() + 1);
			}
			// Người đánh
			caroView.setPlayer(Player.X);
			return true;
		}
		return false;
	}

	/**
	 * Phương thức reset bàn cờ
	 * 
	 * @param arrLabel
	 *            Mảng các ô cờ
	 */
	public void resetBoard(JLabel[][] arrLabel) {
		// Reset tất cả các giá trị của label về ""
		for (int i = 0; i < Constant.ROWS; i++) {
			for (int j = 0; j < Constant.COLS; j++) {
				// Gán giá trị về "";
				arrLabel[i][j].setText("");
				// Set biến đếm số nước đi về 0
				caroView.setCount(0);
			}
		}
	}

	/**
	 * Phương thức hỏi đối tượng đánh trước và tiến hành đánh nước đầu tiên nếu
	 * là máy đi trước
	 */
	public void askObMoveFist() {
		resetBoard(arrLabel);
		// Hỏi người chơi ai đánh trước
		int input = JOptionPane.showConfirmDialog(null, "Bạn đi trước");
		// Máy đánh trước
		if (input == JOptionPane.NO_OPTION) {
			// Đánh 1 nước chính sữa bàn cờ
			arrLabel[Constant.ROWS / 2][Constant.COLS / 2].setText("O");
			// Đổi lượt cho người chơi
			caroView.setPlayer(Player.X);
			// Tăng biến đếm nước đi lên 1
			caroView.setCount(caroView.getCount() + 1);
		}
		// Người đánh
		caroView.setPlayer(Player.X);
	}

	/**
	 * Phương thức người chơi thực hiện nước đánh cờ
	 * 
	 * @param lb
	 *            Label cần đánh
	 * @return true : Đánh thành công, false : Đánh không thành công
	 */
	public boolean humanMove(JLabel lb) {
		// Nếu lượt đi là của người chơi
		if (caroView.getPlayer() == Player.X) {
			// Nếu nước đi đó chưa đánh (Label có nhãn rỗng)
			if (lb.getText() == "") {
				// Lấy tọa độ của Label đó
				int x = lb.getX();
				int y = lb.getY();
				// Quy đổi tọa độ về vị trí trong mảng ma trận cờ caro
				int xCol = x / Constant.WIDTH;
				int yRow = y / Constant.HEIGHT;
				// Xét text X cho label của người chơi
				lb.setText("X");
				// Đổi lượt người chơi cho máy
				caroView.setPlayer(Player.convertPlayer(caroView.getPlayer()));
				// Tăng biến đếm số lượng các nước đã đi lên 1
				caroView.setCount(caroView.getCount() + 1);
				// Lấy giá trị kiểm tra chiến thắng của nước vừa đi
				int win = checkWin(new Pieces(yRow, xCol), arrLabel, Player.X);
				// Nếu người chơi thắng thì thông báo bạn thắng
				if (win == Constant.HUMAN_WIN) {
					JOptionPane.showMessageDialog(null, "Bạn thắng");
					// Kết thúc game
					caroView.setPlayGame(false);
					return false;
					// Nếu máy thắng thì thông báo máy thắng
				}
				// Nếu đánh full bàn cờ mà chưa có người thắng (tức là cờ hòa)
				if (caroView.getCount() == Constant.COLS * Constant.ROWS) {
					JOptionPane.showMessageDialog(null, "Hòa!Game kết thúc!!");
					// Kết thúc game
					caroView.setPlayGame(false);
					return false;
				}
				return true;
			}
		}
		// Nếu lượt đi không phải của người chơi thì không tiến hành đánh nước
		// cờ và trả về false
		return false;
	}

	/**
	 * Phương thức máy đánh
	 */
	public void computerMove() {
		// Tiến hành tìm nước cờ phù hợp nhất để máy đánh
		Pieces pieces = getMoveComputer(arrLabel, alFlagChess);
		// Vị trí hàng của nước cần đánh
		int row = pieces.getyRow();
		// Vị trí cột của nước cần đánh
		int col = pieces.getxCol();
		// Nếu nước đi tìm được chưa được đánh vào bàn cờ
		if ("".equals(arrLabel[row][col].getText())) {
			// Gán ô cờ tại vị trí đó có nhãn O
			arrLabel[row][col].setText("O");
			// Tiến hành đổi lượt cho người chơi
			caroView.setPlayer(Player.convertPlayer(caroView.getPlayer()));
			// Tăng biến đếm số lượng các nước đã đi lên 1
			caroView.setCount(caroView.getCount() + 1);
			// Lấy giá trị kiểm tra chiến thắng của nước vừa đi
			int win = checkWin(pieces, arrLabel, Player.O);
			// Nếu người chơi thắng thì thông báo bạn thắng
			if (win == Constant.COMPUTER_WIN) {
				JOptionPane.showMessageDialog(null, "Máy thắng");
				// Kết thúc game
				caroView.setPlayGame(false);
			}
			// Nếu đánh full bàn cờ mà chưa có người thắng (tức là cờ hòa)
			if (caroView.getCount() == Constant.COLS * Constant.ROWS) {
				JOptionPane.showMessageDialog(null, "Hòa!Game kết thúc!!");
				// Kết thúc game
				caroView.setPlayGame(false);
			}
			// Nếu nước tìm được đã được đánh
		} else {
			// Tiến hành tìm kiếm nước đi lại
			computerMove();
		}
	}
}
