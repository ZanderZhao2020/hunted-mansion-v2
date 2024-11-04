import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Room {
	public static final char EMPTY_CHAR = ' ';
	public static final char WALL_CHAR = '=';
	protected StringBuilder map;
	protected List<Monster> monsters;
	protected int width, height;

	public Room(int width, int height) {
		this.width = width;
		this.height = height;
		this.map = new StringBuilder();
		this.monsters = new ArrayList<>();
		for (int x = 0; x < width + 2; x++) {
			this.map.append(Room.WALL_CHAR);
		}
		this.map.append('\n');
		for (int y = 0; y < height; y++) {
			this.map.append(Room.WALL_CHAR);
			for (int x = 0; x < width; x++) {
				this.map.append(Room.EMPTY_CHAR);
			}
			this.map.append(Room.WALL_CHAR);
			this.map.append('\n');
		}
		for (int x = 0; x < width + 2; x++) {
			this.map.append(Room.WALL_CHAR);
		}
		this.map.append('\n');
		this.monsters.add(new Monster(width - 1, height - 1, this, Monster.Type.ZOMBIE));
	}

	public char getMapAt(int x, int y) {
		return this.map.charAt((y + 1) * (this.width + 3) + x + 1);
	}
	
	public void setMapAt(int x, int y, char ch) {
		this.map.setCharAt((y + 1) * (this.width + 3) + x + 1, ch);
	}

	public void tick() {
		Set<Monster> trying = new HashSet<Monster>(monsters);
		boolean chg;
		do {
			chg = false;
			Iterator<Monster> it = trying.iterator();
			while (it.hasNext()) {
				Monster next = it.next();
				int advanced = next.advance(player);
				if (advanced >= 0) {
					it.remove();
				}
				if (advanced == 1) {
					chg = true;
				}
			}
		} while (chg);
	}
	
	@Override
	public String toString() {
		return this.map.toString();
	}
}
