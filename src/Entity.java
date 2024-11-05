public abstract class Entity {
	public static enum Type {
		ZOMBIE("Zombie", 100, 5, 'Z'),
		PLAYER("Player", 100, 80, '@');

		public String name;
		public int startHP;
		public int atk;
		public char mapChar;

		private Type(String name, int startHP, int atk, char mapChar) {
			this.name = name;
			this.startHP = startHP;
			this.atk = atk;
			this.mapChar = mapChar;
		}
	}

	public Type type;
	public int x, y;
	public Room room;
	public Item wpn;
	public int hp;

	public Entity(int x, int y, Room room, Type type) {
		this.x = x;
		this.y = y;
		this.room = room;
		this.type = type;
		this.hp = type.startHP;
		this.room.ents[this.y][this.x] = this;
	}

	public void chgHP(int amt) {
		this.hp += amt;
		if (this.hp > 100) {
			this.hp = 100;
		}
	}

	public void go(int x, int y) {
		this.room.ents[this.y][this.x] = null;
		this.x = x;
		this.y = y;
		this.room.ents[this.y][this.x] = this;
	}
}
