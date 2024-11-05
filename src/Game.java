import java.util.Scanner;

public class Game {
	public static Player player;

	public static void main(String[] args) {
		Room room = new Room(10, 10, 1);
		player = new Player(0, 0, room);
		room.populate();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String msg = "Welcome to the Table 1 Hunted Mansion. Press i and enter for help.\n";
		room.tick();
		while (true) {
			System.out.print(room);
			System.out.print(msg);
			String cmd = scanner.nextLine();
			if (cmd.length() == 0) {
				msg = "Invalid instruction. Press i and enter for help.\n";
				continue;
			}
			Entity playerRet;
			msg = "";
			switch (cmd.charAt(0)) {
			case 'w':
				playerRet = player.advance(Direction.UP);
				break;
			case 's':
				playerRet = player.advance(Direction.DOWN);
				break;
			case 'a':
				playerRet = player.advance(Direction.LEFT);
				break;
			case 'd':
				playerRet = player.advance(Direction.RIGHT);
				break;
			case 'p':
				Item item = player.room.items[player.y][player.x];
				if (item == null) {
					msg = "Nothing there.\n";
					continue;
				}
				msg = "Picked up a " + item.type.name + '\n';
				player.inv.add(item);
				player.room.items[player.y][player.x] = null;
				playerRet = player;
				break;
			case 'i':
				msg = "w: move/attack right\ns: move/attack down\na: move/attack left\nd: move/attack right\ne: use item\np: pickup item\ni: help\n";
				continue;
			default:
				msg = "Invalid instruction. Press i and enter for help.\n";
				continue;
			}
			if (playerRet == null) {
				msg = "Can't move there.\n";
				continue;
			} else if (playerRet instanceof Monster) {
				msg = "Hit " + playerRet.type.name + " for " + player.type.atk + " HP.\n";
			}
			int dmg = room.tick();
			if (dmg > 0) {
				msg += "You were hit for " + dmg + " HP (" + player.hp + " remaining).";
			}
		}
	}
}
