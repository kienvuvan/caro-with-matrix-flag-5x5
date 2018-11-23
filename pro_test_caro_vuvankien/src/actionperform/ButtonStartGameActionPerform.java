/**
 * Copyright (C) 2018 Luvina Academy
 * ButtonStartGameActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package actionperform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.ProcessGame;
import view.CaroView;

/**
 * Class kế từ từ interface ActionListener để lắng nghe sự kiện của button
 * StartGame
 * 
 * @author kien vu
 *
 */
public class ButtonStartGameActionPerform implements ActionListener {

	private CaroView caroView;

	/**
	 * Contructor khởi tạo đối tượng ButtonStartGameActionPerform
	 * 
	 * @param caroView
	 *            Đối tượng CaroView : màn hình game caro
	 */
	public ButtonStartGameActionPerform(CaroView caroView) {
		this.caroView = caroView;
	}

	@Override
	/**
	 * Sự kiện click button StartGame
	 */
	public void actionPerformed(ActionEvent e) {
		// Tạo đối tượng process để xử lý
		ProcessGame processGame = new ProcessGame(caroView);
		// Bắt đầu game
		if(processGame.startGame()){
			// Thông báo bắt đầu game
			JOptionPane.showMessageDialog(null, "Game bat dau");
			// Get button StartGame
			JButton bntStartGame = (JButton) e.getSource();
			// Cho thuộc tính của button StartGame không chọn được khi chơi game
			bntStartGame.setEnabled(false);
			// Get frame
			JFrame frame = caroView.getFrame();
			// Thêm button NewGame với nhãn New game
			JButton btnNewGame = new JButton("New game");
			// Thêm sự kiện lắng nghe để tạo game mới
			btnNewGame.addActionListener(new ButtonNewGameActionPerform(caroView));
			// Set vị trí và kích thước cho button New Game
			btnNewGame.setBounds(520, 60, 118, 23);
			// Thêm button new game vào frame
			frame.getContentPane().add(btnNewGame);
		}
	}

}
