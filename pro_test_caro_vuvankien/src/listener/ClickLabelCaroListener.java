/**
 * Copyright (C) 2018 Luvina Academy
 * LabelCaroActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logic.CheckWinGame;
import logic.FindMoveComputer;
import model.Pieces;
import model.Player;
import utils.Constant;
import view.CaroView;

/**
 * Class kế thừa từ interface MouseListener để lắng nghe sự kiện của JLabel
 * 
 * @author kien vu
 *
 */
public class ClickLabelCaroListener implements MouseListener {

	private CaroView caroView;

	/**
	 * Contructor khởi tạo đối tượng LabelCaroActionPerform
	 * 
	 * @param caroView
	 *            Đối tượng CaroView : màn hình game caro
	 */
	public ClickLabelCaroListener(CaroView caroView) {
		this.caroView = caroView;
	}

	@Override
	// Sự kiện click chuột vào label
	public void mouseClicked(MouseEvent e) {
		// Nếu tình trạng chơi game là true (đang chơi)
		if (caroView.isPlayGame()) {
			// Tiến hành thực hiện nước đi người đánh
			boolean check = humanMove((JLabel) e.getSource());
			// Nếu người đánh thành công
			if (check) {
				// Máy đánh
				computerMove();
			}
		} else {
			String type = caroView.getBtnGame().getText();
			JOptionPane.showMessageDialog(null, "Bấm " + type + " để chơi");
		}
	}

	/**
	 * Phương thức người chơi thực hiện nước đánh cờ
	 * 
	 * @param lb
	 *            Label cần đánh
	 * @return true : Đánh thành công, false : Đánh không thành công
	 */
	public boolean humanMove(JLabel labelHumanMove) {
		// Nếu lượt đi là của người chơi
		if (caroView.getPlayer() == Player.X) {
			// Nếu nước đi đó chưa đánh (Label có nhãn rỗng)
			if ("".equals(labelHumanMove.getText())) {
				// Lấy tọa độ của Label đó
				int x = labelHumanMove.getX();
				int y = labelHumanMove.getY();
				// Quy đổi tọa độ về vị trí trong mảng ma trận cờ caro
				int xCol = x / Constant.WIDTH;
				int yRow = y / Constant.HEIGHT;
				// Xét text X cho label của người chơi
				labelHumanMove.setText("X");
				// Đổi lượt người chơi cho máy
				caroView.setPlayer(Player.convertPlayer(caroView.getPlayer()));
				// Tăng biến đếm số lượng các nước đã đi lên 1
				caroView.setCount(caroView.getCount() + 1);
				CheckWinGame checkWinGame = new CheckWinGame();
				// Lấy giá trị kiểm tra chiến thắng của nước vừa đi
				boolean win = checkWinGame.checkWin(new Pieces("X", yRow, xCol), caroView.getArrLabel());
				// Nếu người chơi thắng thì thông báo bạn thắng
				if (win) {
					JOptionPane.showMessageDialog(null, "Bạn thắng");
					// Kết thúc game
					caroView.setPlayGame(false);
					return false;
				}
				// Nếu đánh full bàn cờ mà chưa có người thắng (tức là cờ hòa)
				if (caroView.getCount() == Constant.COLS * Constant.ROWS) {
					JOptionPane.showMessageDialog(null, "Hòa!Game kết thúc!!");
					// Kết thúc game
					caroView.setPlayGame(false);
					return false;
				}
				// Người đánh thành công thì trả về true để đến lượt máy đánh
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
		JLabel[][] arrLabel = caroView.getArrLabel();
		// Tiến hành tìm nước cờ phù hợp nhất để máy đánh
		FindMoveComputer findMoveComputer = new FindMoveComputer();
		Pieces pieces = findMoveComputer.getMoveComputer(arrLabel, caroView.getAlFlagChess());
		// Vị trí hàng của nước cần đánh
		int row = pieces.getRow();
		// Vị trí cột của nước cần đánh
		int col = pieces.getCol();
		// Nếu nước đi tìm được chưa được đánh vào bàn cờ
		if ("".equals(arrLabel[row][col].getText())) {
			// Gán ô cờ tại vị trí đó có nhãn O
			arrLabel[row][col].setText("O");
			// Tiến hành đổi lượt cho người chơi
			caroView.setPlayer(Player.convertPlayer(caroView.getPlayer()));
			// Tăng biến đếm số lượng các nước đã đi lên 1
			caroView.setCount(caroView.getCount() + 1);
			CheckWinGame checkWinGame = new CheckWinGame();
			// Lấy giá trị kiểm tra chiến thắng của nước vừa đi
			boolean win = checkWinGame.checkWin(pieces, arrLabel);
			// Nếu người chơi thắng thì thông báo bạn thắng
			if (win) {
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
