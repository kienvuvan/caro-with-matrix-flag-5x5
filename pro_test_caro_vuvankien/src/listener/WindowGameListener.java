/**
 * Copyright (C) 2018 Luvina Academy
 * CaroView.java 12/11/2018, Vũ Văn Kiên
 */
package listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

/**
 * Class kế từ từ interface WindowListener để lắng nghe sự kiện đối với cửa sổ
 * của game
 * 
 * @author kien vu
 *
 */
public class WindowGameListener implements WindowListener {

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Phương thức thực hiện khi cửa sổ game đang chuẩn bị đóng
	 *
	 */
	public void windowClosing(WindowEvent e) {
		// Hiển thị thông báo hỏi xem bạn có muốn xóa hay không?
		int input = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không?", "Thoát game",
				JOptionPane.YES_NO_OPTION);
		// Kiểm tra sự lựa chọn
		switch (input) {
		// Nếu đồng ý thoát
		case JOptionPane.YES_OPTION:
			// Thoát chương trình
			System.exit(0);
			break;
		// Nếu không muốn thoát
		default:
			// Không xử lý thoát và tiếp tục game
			break;
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
