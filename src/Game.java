public class Game {
	private static Player player;

	public static Player getPlayer() {
		return Game.player;
	}

	public static void main(String[] args) {
		Room room = new Room(10, 10);
		Player player = new Player(0, 0, room);
		System.out.print(room);
	}
}
