/**
 * Copyright (C) 2018 Luvina Academy
 * CaroView.java 12/11/2018, Vũ Văn Kiên
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import actionperform.ButtonGameCaroActionPerform;
import exception.EmptyListFlagChessException;
import listener.ClickLabelCaroListener;
import listener.WindowGameListener;
import logic.FindMoveComputer;
import model.Player;
import utils.Constant;

/**
 * Class giao diện game Caro
 * 
 * @author kien vu
 *
 */
public class CaroView {

	private JFrame frame;
	private Player player = Player.X;
	private int count = 0;
	private boolean playGame;
	private JLabel[][] arrLabel = new JLabel[Constant.ROWS][Constant.COLS];
	private static ArrayList<String> alFlagChess;
	private JButton btnGame;
	private FindMoveComputer findMoveComputer;

	/**
	 * Create the application.
	 * 
	 * @param boolean
	 *            playGame : Biến truyền vào để xác định tình trạng chơi game
	 *            của bàn cờ
	 * @throws IOException,
	 *             EmptyStackException
	 * @throws EmptyListFlagChessException
	 * @throws Exception
	 */
	public CaroView(boolean playGame) throws IOException, EmptyListFlagChessException {
		// Thiết kế giao diện game
		initialize();
		// Gán giá trị tình trạng chơi game bằng tham số truyền vào
		this.playGame = playGame;
		try {
			// Tạo đối tượng FindMoveComputer
			findMoveComputer = new FindMoveComputer();
			// Lấy danh sách thế cờ 5x5
			alFlagChess = findMoveComputer.getAllMove();
			// Nếu file rỗng
			if (alFlagChess.isEmpty()) {
				throw new EmptyListFlagChessException();
			}
		} catch (IOException e) {
			e.getMessage();
			throw e;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Tạo frame
		frame = new JFrame();
		// Tạo tiêu đề cho frame
		frame.setTitle("Game Caro with Computer");
		// Tạo đối tượng ảnh lấy từ đường dẫn ảnh
		Image image = Toolkit.getDefaultToolkit().getImage("src/res/caro.jpg");
		// Set icon app caro
		frame.setIconImage(image);
		// Set trục x, trục y, độ rộng và chiều cao của frame
		frame.setBounds(100, 100, 663, 559);
		// Set thuộc tính không xử lý thoát chương trình khi click nút X góc
		// phải màn hình
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Set layout cho frame (Không sử dụng layout)
		frame.getContentPane().setLayout(null);
		// Cho frame ra chính giữa màn hình
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		// Tạo Panel
		JPanel panel = new JPanel();
		// Set vị trí của panel và kích thước của bàn cờ
		panel.setBounds(11, 11, Constant.COLS * Constant.WIDTH, Constant.ROWS * Constant.HEIGHT);
		// Set layout cho panel
		panel.setLayout(new GridLayout(Constant.ROWS, Constant.COLS, 0, 0));
		// Vẽ bàn cờ caro
		paintBoard(panel);
		// Thêm panel vào frame
		frame.getContentPane().add(panel);
		// Tạo button StartGame với nhãn Start game
		btnGame = new JButton("Start Game");
		// Thêm sự kiện lắng nghe để bắt đầu game
		btnGame.addActionListener(new ButtonGameCaroActionPerform(this));
		// Set vị trí và kích thước cho button Start Game
		btnGame.setBounds(520, 11, 118, 23);
		// Thêm button start game vào frame
		frame.getContentPane().add(btnGame);
		// Thêm sự kiện lắng nghe sự kiện cửa sổ của game
		frame.addWindowListener(new WindowGameListener());
	}

	/**
	 * Phương thức vẽ bàn cờ caro có kích thước Board.ROW * Board.HEIGHT
	 *
	 * @param panel
	 *            JPanel chứa bàn cờ caro
	 */
	private void paintBoard(JPanel panel) {
		// Vẽ bàn cờ 20x20
		for (int i = 0; i < Constant.ROWS; i++) {
			for (int j = 0; j < Constant.COLS; j++) {
				arrLabel[i][j] = new JLabel("");
				JLabel lb = arrLabel[i][j];
				// Thêm label vào trong panel
				panel.add(lb);
				// Tạo border và set border cho label
				Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
				lb.setBorder(border);
				// Set font chữ cho label
				lb.setFont(new Font("SansSerif", Font.PLAIN, 20));
				// Set thuộc tính text ở giữa label
				lb.setVerticalAlignment(SwingConstants.CENTER);
				lb.setHorizontalAlignment(SwingConstants.CENTER);
				// Thêm sự kiện click chuột cho label
				lb.addMouseListener(new ClickLabelCaroListener(this));
			}
		}
	}

	/**
	 * Phương thức lấy thông tin về tình trạng của game (đang chơi hoặc kết
	 * thúc)
	 * 
	 * @return the playGame tình trạng của game
	 */
	public boolean isPlayGame() {
		return playGame;
	}

	/**
	 * Phương thức gán giá trí tình trạng chơi game bằng tham số truyền vào
	 * 
	 * @param playGame
	 *            the playGame tình trạng game true hoặc false
	 */
	public void setPlayGame(boolean playGame) {
		this.playGame = playGame;
	}

	/**
	 * Phương thức lấy thông tin người chơi
	 * 
	 * @return the player thông tin người chơi
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Phương thức gán người chơi cho tham số truyền vào
	 * 
	 * @param player
	 *            the player người chơi X hoặc O
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the alFlagChess
	 */
	public ArrayList<String> getAlFlagChess() {
		return alFlagChess;
	}

	/**
	 * @param alFlagChess
	 *            the alFlagChess to set
	 */
	public void setAlFlagChess(ArrayList<String> alFlagChess) {
		this.alFlagChess = alFlagChess;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the arrLabel
	 */
	public JLabel[][] getArrLabel() {
		return arrLabel;
	}

	/**
	 * @param arrLabel
	 *            the arrLabel to set
	 */
	public void setArrLabel(JLabel[][] arrLabel) {
		this.arrLabel = arrLabel;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame
	 *            the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * @return the btnGame
	 */
	public JButton getBtnGame() {
		return btnGame;
	}

	/**
	 * @param btnGame the btnGame to set
	 */
	public void setBtnGame(JButton btnGame) {
		this.btnGame = btnGame;
	}
	
}
