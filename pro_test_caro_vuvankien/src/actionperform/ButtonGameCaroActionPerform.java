/**
 * Copyright (C) 2018 Luvina Academy
 * ButtonNewGameActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package actionperform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Player;
import utils.Constant;
import view.CaroView;

/**
 * Class kế từ từ interface ActionListener để lắng nghe sự kiện của button
 * NewGame
 * 
 * @author kien vu
 *
 */
public class ButtonGameCaroActionPerform implements ActionListener {
	private CaroView caroView;
	private JLabel[][] arrLabel;

	// Phương thức khởi tạo đối tượng ButtonNewGameActionPerform
	public ButtonGameCaroActionPerform(CaroView caroView) {
		this.caroView = caroView;
		arrLabel = caroView.getArrLabel();
	}

	@Override
	// Sự kiện click button NewGame
	public void actionPerformed(ActionEvent e) {
		// Lấy button game
		JButton btnGame = (JButton) e.getSource();
		// Lấy giá trị text của button đó
		String typeGame = btnGame.getText();
		switch (typeGame) {
		// Nếu text là Start Game
		case "Start Game":
			// Nếu bắt đầu game thành công
			if (setStartGame()) {
				// Đổi giá trị text của button thành New Game
				btnGame.setText("New Game");
			}
			break;
		// Nếu text là New Game (Nghĩa là đã chơi 1 lần rồi)
		case "New Game":
			// Gọi đến phương thức newGame()
			newGame();
			break;
		}
	}

	/**
	 * Phương thức tạo game mới
	 */
	public void newGame() {
		// Nếu game đang chơi
		if (caroView.isPlayGame()) {
			// Hỏi người chơi có muốn chơi lại không?
			int input = JOptionPane.showConfirmDialog(null, "Bạn có muốn chơi lại không ?", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			switch (input) {
			// Nếu đồng ý chơi lại, thì tạo game mới
			case JOptionPane.YES_OPTION:
				setStartGame();
				break;
			// Ngược lại, tiếp tục chơi
			default:
				break;
			}
		} else {
			// Nếu game đã kết thúc thì sẽ thông báo game mới
			JOptionPane.showMessageDialog(null, "Game mới");
			// Bắt đầu game
			setStartGame();
		}
	}

	/**
	 * Phương thức bắt đầu game
	 */
	public boolean setStartGame() {
		// Hỏi người chơi ai đánh trước
		int input = JOptionPane.showConfirmDialog(null, "Bạn đi trước");
		// Máy đánh trước
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

}
