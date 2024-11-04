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
	 * Approach the player or attack if possible. Returns -1 if I was blocked, 0 if I
	 * attacked, or 1 if I moved.
	 */
	public int advance() {
		List<Direction> dirs = new ArrayList<>();
		if (Game.getPlayer().getX() < this.x) {
			char at = this.room.getMapAt(this.x - 1, this.y);
			if (at == Room.PLAYER_CHAR) {
				Game.getPlayer().chgHP(-this.type.atk);
				return 0;
			}
			if (at == Room.EMPTY_CHAR) {
				dirs.add(Direction.LEFT);
			}
		} else if (Game.getPlayer().getX() > this.x) {
			char at = this.room.getMapAt(this.x + 1, this.y);
			if (at == Room.PLAYER_CHAR) {
				Game.getPlayer().chgHP(-this.type.atk);
				return 0;
			}
			if (at == Room.EMPTY_CHAR) {
				dirs.add(Direction.RIGHT);
			}
		}
		if (Game.getPlayer().getY() < this.y) {
			char at = this.room.getMapAt(this.x, this.y - 1);
			if (at == Room.PLAYER_CHAR) {
				Game.getPlayer().chgHP(-this.type.atk);
				return 0;
			}
			if (at == Room.EMPTY_CHAR) {
				dirs.add(Direction.UP);
			}
		} else if (Game.getPlayer().getY() > this.y) {
			char at = this.room.getMapAt(this.x, this.y + 1);
			if (at == Room.PLAYER_CHAR) {
				Game.getPlayer().chgHP(-this.type.atk);
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
