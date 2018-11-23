/**
 * Copyright (C) 2018 Luvina Academy
 * ButtonNewGameActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package actionperform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.ProcessGame;
import view.CaroView;

/**
 * Class kế từ từ interface ActionListener để lắng nghe sự kiện của button
 * NewGame
 * 
 * @author kien vu
 *
 */
public class ButtonNewGameActionPerform implements ActionListener {
	private CaroView caroView;

	// Phương thức khởi tạo đối tượng ButtonNewGameActionPerform
	public ButtonNewGameActionPerform(CaroView caroView) {
		this.caroView = caroView;
	}

	@Override
	// Sự kiện click button NewGame
	public void actionPerformed(ActionEvent e) {
		// Tạo đối tượng để xử lý tạo game mới
		ProcessGame processGame = new ProcessGame(caroView);
		// Tạo game mới
		if(processGame.newGame()){
			// Thông báo game mới
			JOptionPane.showMessageDialog(null, "Game mới");
		}
	}

}
