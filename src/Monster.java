import java.util.ArrayList;
import java.util.List;

public class Monster extends Entity {
	public enum Type {
		ZOMBIE("Zombie", 100, 10, 'Z');

		protected String name;
		protected int startHP;
		protected int atk;
		protected char mapChar;

		private Type(String name, int startHP, int atk, char mapChar) {
			this.name = name;
			this.startHP = startHP;
			this.atk = atk;
			this.mapChar = mapChar;
		}

		public String getName() {
			return this.name;
		}

		public int getStartHP() {
			return this.startHP;
		}

		public int getAtk() {
			return this.atk;
		}

		public char getMapChar() {
			return this.mapChar;
		}
	}

	protected Type type;

	public Monster(int x, int y, Room room, Type type) {
		super(x, y, room);
		this.type = type;
		this.hp = type.startHP;
		this.room.setMapAt(this.x, this.y, this.type.mapChar);
	}

	public Type getType() {
		return this.type;
	}

	/**
	 * Approach the player or attack if possible. Returns 0 was blocked, 1 if I
	 * attacked, or 2 if I moved.
	 */
	public int advance(Player player) {
		List<Direction> dirs = new ArrayList<>();
		if (player.getX() < this.x) {
			char at = this.room.getMapAt(this.x - 1, this.y);
			if (at == Room.PLAYER_CHAR) {
				player.chgHP(-this.type.atk);
				return 0;
			}
			if (at == Room.EMPTY_CHAR) {
				dirs.add(Direction.LEFT);
			}
		} else if (player.getX() > this.x) {
			char at = this.room.getMapAt(this.x + 1, this.y);
			if (at == Room.PLAYER_CHAR) {
				player.chgHP(-this.type.atk);
				return 0;
			}
			if (at == Room.EMPTY_CHAR) {
				dirs.add(Direction.RIGHT);
			}
		}
		if (player.getY() < this.y) {
			char at = this.room.getMapAt(this.x, this.y - 1);
			if (at == Room.PLAYER_CHAR) {
				player.chgHP(-this.type.atk);
				return 0;
			}
			if (at == Room.EMPTY_CHAR) {
				dirs.add(Direction.UP);
			}
		} else if (player.getY() > this.y) {
			char at = this.room.getMapAt(this.x, this.y + 1);
			if (at == Room.PLAYER_CHAR) {
				player.chgHP(-this.type.atk);
				return 0;
			}
			if (at == Room.EMPTY_CHAR) {
				dirs.add(Direction.DOWN);
			}
		}
		if (dirs.isEmpty()) {
			return -1;
		}
		this.room.setMapAt(this.x, this.y, Room.EMPTY_CHAR);
		switch (dirs.get((int) (Math.random() * dirs.size()))) {
		case LEFT:
			this.x--;
			break;
		case RIGHT:
			this.x++;
			break;
		case UP:
			this.y--;
			break;
		case DOWN:
			this.y++;
			break;
		}
		this.room.setMapAt(this.x, this.y, this.type.mapChar);
		return 1;
	}
}
