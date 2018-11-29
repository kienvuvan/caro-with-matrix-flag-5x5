package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFileFlagChess {

	/**
	 * Phương thức lấy danh sách tất cả các thế cờ ma trận 5 x5 trong file thế
	 * cờ**@return ArrayList<String> danh sách tất cả các thế cờ*@throws
	 * IOException*@throws Exception* Không tìm được file hoặc lỗi trong quá
	 * trình đọc file
	 */

	public ArrayList<String> getAllMove() throws IOException {
		// Tạo list<String>
		ArrayList<String> alFlag = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			// Tạo đối tượng file có đường dẫn đến file ma trận thế cờ
			File fileMaxtrix = new File("src/common/matrixFlagChess.txt");
			// Tạo luồng đọc file thế cờ
			bufferedReader = new BufferedReader(new FileReader(fileMaxtrix));
			String out;
			// Tạo đối tượng StringBuider
			StringBuilder result = new StringBuilder("");
			// Tạo biến đếm
			int count = 0;
			// Đọc toàn bộ file
			while ((out = bufferedReader.readLine()) != null) {
				// Nối dòng đọc được vào StringBuider và loại bỏ dấu cách trong
				// chuỗi đọc được
				result.append(out.replaceAll(" ", ""));
				// Tăng biến đếm dòng lên 1
				count++;
				// Nếu biến đếm chia hết cho 6 (tính cả dòng cách ra) nghĩa là
				// có 1 ma trận thế cờ 5x5
				if (count % 6 == 0) {
					// Thêm vào danh sách
					alFlag.add(result.toString());
					// Reset chuỗi trong StringBuider về 0
					result.delete(0, result.length());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw e;
		} finally {
			// Đọc xong thì kiểm tra nếu luồng chưa được đóng thì đóng luồng đọc
			// file lại
			if (bufferedReader != null) {
				// Đóng luồng
				bufferedReader.close();
			}
		}
		// Trả về danh sách thế cờ
		return alFlag;
	}

}
