/**
 * Copyright (C) 2018 Luvina Academy
 * LabelCaroActionPerform.java 12/11/2018, Vũ Văn Kiên
 */
package exception;

/**
 * Class kế thừa Class Exception để bắt lỗi mảng, danh sách trống
 * 
 * @author kien vu
 *
 */
public class EmptyListFlagChessException extends Exception {

	/**
	 * Phương thức xử lý lỗi khi danh sách thế cờ rỗng
	 */
	public EmptyListFlagChessException() {
		System.out.println("File thế cờ rỗng");
	}
}
