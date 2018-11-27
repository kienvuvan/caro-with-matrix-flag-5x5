/**
 * Copyright (C) 2018 Luvina Academy
 * Main.java 12/11/2018, Vũ Văn Kiên
 */
package logic;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import view.CaroView;

/**
 * Class chạy chương trình Game Caro
 * 
 * @author kien vu
 *
 */
public class RunMain {
	/**
	 * Phương thức main dùng để chạy chương trình
	 * 
	 * @param args
	 *            Chuỗi tham số mặc định truyền vào khi chạy chương trình
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Khởi tạo đối tượng CaroView
					CaroView window = new CaroView(false);
					// Hiển thị giao diện chơi game lên
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Game đang lỗi");
				}
			}
		});
	}
}
