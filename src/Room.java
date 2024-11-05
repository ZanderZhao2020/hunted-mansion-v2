import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Room {
	public Item[][] items;
	public Entity[][] ents;
	public List<Monster> monsters;
	public int width, height;
	public int difficulty;

	public Room(int width, int height, int difficulty) {
		this.width = width;
		this.height = height;
		this.items = new Item[height][width];
		this.ents = new Entity[height][width];
		this.monsters = new ArrayList<>();
		this.difficulty = difficulty;
	}

	public void populate() {
		int itemsWanted = (int) (Math.random() * 3);
		int itemsAdded = 0;
		do {
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);
			if (items[y][x] == null) {
				items[y][x] = new Item(Item.Type.HEAL_POTION);
				itemsAdded++;
			}
		} while (itemsAdded < itemsWanted);
		int monstersAdded = 0;
		do {
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);
			if (ents[y][x] == null) {
				System.out.println("added monster");
				this.monsters.add(new Monster(x, y, this, Entity.Type.ZOMBIE));
				monstersAdded++;
			}
		} while (monstersAdded < difficulty);
	}

	public int tick() {
		Set<Monster> trying = new HashSet<Monster>(monsters);
		boolean chg;
		int ans = 0;
		do {
			chg = false;
			Iterator<Monster> it = trying.iterator();
			while (it.hasNext()) {
				Monster next = it.next();
				int ret = next.advance();
				if (ret >= 0) {
					it.remove();
					if (ret == 0) {
						chg = true;
					} else {
						ans += ret;
					}
				}
			}
		} while (chg);
		return ans;
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
				ans.append(this.ents[y][x] == null ? (this.items[y][x] == null ? ' ' : 'I') : this.ents[y][x].type.mapChar);
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
