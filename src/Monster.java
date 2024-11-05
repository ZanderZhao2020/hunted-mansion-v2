import java.util.ArrayList;
import java.util.List;

public class Monster extends Entity {
	public Monster(int x, int y, Room room, Entity.Type type) {
		super(x, y, room, type);
	}

	public int advance() {
		List<Direction> dirs = new ArrayList<>();
		if (Game.player.x < this.x) {
			Entity at = this.room.ents[this.y][this.x - 1];
			if (at == null) {
				dirs.add(Direction.LEFT);
			} else if (at instanceof Player) {
				at.chgHP(-this.type.atk);
				return this.type.atk;
			}
		} else if (Game.player.x > this.x) {
			Entity at = this.room.ents[this.y][this.x + 1];
			if (at == null) {
				dirs.add(Direction.RIGHT);
			} else if (at instanceof Player) {
				at.chgHP(-this.type.atk);
				return this.type.atk;
			}
		}
		if (Game.player.y < this.y) {
			Entity at = this.room.ents[this.y - 1][this.x];
			if (at == null) {
				dirs.add(Direction.UP);
			} else if (at instanceof Player) {
				at.chgHP(-this.type.atk);
				return this.type.atk;
			}
		} else if (Game.player.y > this.y) {
			Entity at = this.room.ents[this.y + 1][this.x];
			if (at == null) {
				dirs.add(Direction.DOWN);
			} else if (at instanceof Player) {
				at.chgHP(-this.type.atk);
				return this.type.atk;
			}
		}
		if (dirs.isEmpty()) {
			return -1;
		}
		switch (dirs.get((int) (Math.random() * dirs.size()))) {
		case LEFT:
			this.go(this.x - 1, this.y);
			break;
		case RIGHT:
			this.go(this.x + 1, this.y);
			break;
		case UP:
			this.go(this.x, this.y - 1);
			break;
		case DOWN:
			this.go(this.x, this.y + 1);
			break;
		}
		return 0;
	}

	@Override
	public void chgHP(int amt) {
		super.chgHP(amt);
		if (this.hp <= 0) {
			this.room.ents[this.y][this.x] = null;
			this.room.monsters.remove(this);
		}
	}
}
