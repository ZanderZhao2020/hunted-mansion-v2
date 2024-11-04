import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
	public enum Type {
		ZOMBIE("Zombie", 100, 5, 'Z'), PLAYER("Player", 100, 80, '@');

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
	protected int x, y;
	protected Room room;
	protected List<Item> inv;
	protected int hp;

	public Entity(int x, int y, Room room) {
		this.x = x;
		this.y = y;
		this.room = room;
		this.inv = new ArrayList<Item>();
		this.room.setEntAt(this.x, this.y, this);
	}

	public Type getType() {
		return this.type;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getHP() {
		return this.hp;
	}

	/**
	 * Returns true if I died, false otherwise.
	 */
	public void chgHP(int amt) {
		this.hp += amt;
		if (this.hp > 100) {
			this.hp = 100;
		}
	}

	public void go(int x, int y) {
		this.room.setEntAt(this.x, this.y, null);
		this.x = x;
		this.y = y;
		this.room.setEntAt(x, y, this);
	}
}
