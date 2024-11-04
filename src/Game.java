import java.util.Scanner;

public class Game {
	private static Player player;

	public static Player getPlayer() {
		return Game.player;
	}

	public static void main(String[] args) {
		Room room = new Room(10, 10);
		Game.player = new Player(0, 0, room);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String msg = "Welcome to the Table 1 Hunted Mansion. Press i and enter for help.\n";
		room.tick();
		while (true) {
			for (int i = 0; i < 50; i++) {
				System.out.println();
			}
			System.out.print(room);
			System.out.print(msg);
			String cmd = scanner.nextLine();
			if (cmd.length() == 0) {
				msg = "Invalid instruction. Press i and enter for help.\n";
				continue;
			}
			AdvanceStatus stat;
			switch (cmd.charAt(0)) {
			case 'w':
				stat = Game.player.advance(Direction.UP);
				break;
			case 's':
				stat = Game.player.advance(Direction.DOWN);
				break;
			case 'a':
				stat = Game.player.advance(Direction.LEFT);
				break;
			case 'd':
				stat = Game.player.advance(Direction.RIGHT);
				break;
			case 'i':
				msg = "Type a character and press enter\nw: move/attack right\ns: move/attack down\na: move/attack left\nd: move/attack right\ne: use item\n";
				continue;
			default:
				msg = "Invalid instruction. Press i and enter for help.\n";
				continue;
			}
			switch (stat) {
			case BLOCKED:
				msg = "Can't move there.\n";
				continue;
			case ATTACKED:
				msg = "Attacked.\n";
				break;
			case MOVED:
				msg = "";
				break;
			}
			room.tick();
			msg += Game.player.getHP() + " HP remaining.";
		}
	}
}
