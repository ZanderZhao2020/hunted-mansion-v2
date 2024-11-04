import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
	protected int x, y;
	protected Room room;
	protected List<Item> inv;
	protected int hp;

	public Entity(int x, int y, Room room) {
		this.x = x;
		this.y = y;
		this.room = room;
		this.inv = new ArrayList<Item>();
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
	public boolean chgHP(int amt) {
		this.hp += amt;
		if (this.hp > 100) {
			this.hp = 100;
		}
		return this.hp <= 0;
	}
}
