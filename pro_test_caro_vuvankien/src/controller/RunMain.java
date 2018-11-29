/**
 * Copyright (C) 2018 Luvina Academy
 * Main.java 12/11/2018, VÅ© VÄƒn KiÃªn
 */
package controller;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import view.CaroView;

/**
 * Class cháº¡y chÆ°Æ¡ng trÃ¬nh Game Caro
 * 
 * @author kien vu
 *
 */
public class RunMain {
	/**
	 * Phương thức main dùng để chạy chương trình
	 * 
	 * @param args
	 *            Chuỗi tham số được truyền vào để chạy chương trình
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Khởi tại đối tượng CaroView
					CaroView window = new CaroView(false);
					// Hiển thị giao diện game lên
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Game đang lỗi");
				}
			}
		});
	}
}
