import java.util.ArrayList;
import java.util.List;

public class Monster extends Entity {
	public Monster(int x, int y, Room room, Entity.Type type) {
		super(x, y, room);
		this.type = type;
		this.hp = type.startHP;
	}

	/**
	 * Approach the player or attack if possible. Returns -1 if I was blocked, 0 if
	 * I attacked, or 1 if I moved.
	 */
	public AdvanceStatus advance() {
		List<Direction> dirs = new ArrayList<>();
		if (Game.getPlayer().getX() < this.x) {
			Entity at = this.room.getEntAt(this.x - 1, this.y);
			if (at == null) {
				dirs.add(Direction.LEFT);
			} else if (at instanceof Player) {
				at.chgHP(-this.type.atk);
				return AdvanceStatus.ATTACKED;
			}
		} else if (Game.getPlayer().getX() > this.x) {
			Entity at = this.room.getEntAt(this.x + 1, this.y);
			if (at == null) {
				dirs.add(Direction.RIGHT);
			} else if (at instanceof Player) {
				at.chgHP(-this.type.atk);
				return AdvanceStatus.ATTACKED;
			}
		}
		if (Game.getPlayer().getY() < this.y) {
			Entity at = this.room.getEntAt(this.x, this.y - 1);
			if (at == null) {
				dirs.add(Direction.UP);
			} else if (at instanceof Player) {
				at.chgHP(-this.type.atk);
				return AdvanceStatus.ATTACKED;
			}
		} else if (Game.getPlayer().getY() > this.y) {
			Entity at = this.room.getEntAt(this.x, this.y + 1);
			if (at == null) {
				dirs.add(Direction.DOWN);
			} else if (at instanceof Player) {
				at.chgHP(-this.type.atk);
				return AdvanceStatus.ATTACKED;
			}
		}
		if (dirs.isEmpty()) {
			return AdvanceStatus.BLOCKED;
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
		return AdvanceStatus.MOVED;
	}

	@Override
	public void chgHP(int amt) {
		super.chgHP(amt);
		if (this.hp <= 0) {
			this.room.removeMonster(this);
		}
	}
}
