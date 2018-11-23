/**
 * Copyright (C) 2018 Luvina Academy
 * LabelCaroActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package actionperform;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.ProcessGame;
import view.CaroView;

/**
 * Class kế từ từ interface MouseListener để lắng nghe sự kiện của JLabel
 * 
 * @author kien vu
 *
 */
public class LabelCaroActionPerform implements MouseListener {

	private CaroView caroView;

	/**
	 * Contructor khởi tạo đối tượng LabelCaroActionPerform
	 * 
	 * @param caroView
	 *            Đối tượng CaroView : màn hình game caro
	 */
	public LabelCaroActionPerform(CaroView caroView) {
		this.caroView = caroView;
	}

	@Override
	// Sự kiện click chuột vào label
	public void mouseClicked(MouseEvent e) {
		// Nếu tình trạng chơi game là true (đang chơi)
		if (caroView.isPlayGame()) {
			// Tạo đối tượng process
			ProcessGame processGame = new ProcessGame(caroView);
			// Tiến hành thực hiện nước đi người đánh
			boolean check = processGame.humanMove((JLabel) e.getSource());
			// Nếu người đánh thành công
			if (check) {
				// Máy đánh
				processGame.computerMove();
			}
		}else{
			JOptionPane.showMessageDialog(null, "Start game để chơi");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
