package model;

public enum Player {
	X, O;

	public static Player convertPlayer(Player player) {
		if (player == Player.O) {
			return Player.X;
		} else {
			return Player.O;
		}
	}

}
