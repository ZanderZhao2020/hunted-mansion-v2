import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Room {
	protected Entity[][] map;
	protected List<Monster> monsters;
	protected int width, height;

	public Room(int width, int height) {
		this.width = width;
		this.height = height;
		this.map = new Entity[height][width];
		this.monsters = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			for (int o = 1; o <= 3; o++) {
				this.monsters.add(new Monster(width - i, height - o, this, Entity.Type.ZOMBIE));
			}
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Entity getEntAt(int x, int y) {
		return this.map[y][x];
	}

	public void setEntAt(int x, int y, Entity ent) {
		this.map[y][x] = ent;
	}

	public void removeMonster(Monster monster) {
		this.map[monster.getY()][monster.getX()] = null;
		this.monsters.remove(monster);
	}

	public void tick() {
		Set<Monster> trying = new HashSet<Monster>(monsters);
		boolean chg;
		do {
			chg = false;
			Iterator<Monster> it = trying.iterator();
			while (it.hasNext()) {
				Monster next = it.next();
				AdvanceStatus stat = next.advance();
				if (stat != AdvanceStatus.BLOCKED) {
					it.remove();
				}
				if (stat == AdvanceStatus.MOVED) {
					chg = true;
				}
			}
		} while (chg);
	}

	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		for (int x = 0; x < width + 2; x++) {
			ans.append('=');
		}
		ans.append('\n');
		for (int y = 0; y < height; y++) {
			ans.append('=');
			for (int x = 0; x < width; x++) {
				ans.append(this.map[y][x] == null ? ' ' : this.map[y][x].getType().getMapChar());
			}
			ans.append("=\n");
		}
		for (int x = 0; x < width + 2; x++) {
			ans.append('=');
		}
		ans.append('\n');
		return ans.toString();
	}
}
