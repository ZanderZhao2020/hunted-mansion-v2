
public class Player extends Entity {

	public Player(int x, int y, Room room) {
		super(x, y, room);
		this.type = Entity.Type.PLAYER;
		this.hp = Entity.Type.PLAYER.getStartHP();
	}

	public AdvanceStatus advance(Direction dir) {
		Entity at = null;
		switch (dir) {
		case LEFT:
			if (this.x <= 0) {
				return AdvanceStatus.BLOCKED;
			}
			at = this.room.getEntAt(this.x - 1, this.y);
			if (at == null) {
				this.go(this.x - 1, this.y);
				return AdvanceStatus.MOVED;
			}
			break;
		case RIGHT:
			if (this.x >= this.room.getWidth() - 1) {
				return AdvanceStatus.BLOCKED;
			}
			at = this.room.getEntAt(this.x + 1, this.y);
			if (at == null) {
				this.go(this.x + 1, this.y);
				return AdvanceStatus.MOVED;
			}
			break;
		case UP:
			if (this.y <= 0) {
				return AdvanceStatus.BLOCKED;
			}
			at = this.room.getEntAt(this.x, this.y - 1);
			if (at == null) {
				this.go(this.x, this.y - 1);
				return AdvanceStatus.MOVED;
			}
			break;
		case DOWN:
			if (this.y >= this.room.getHeight() - 1) {
				return AdvanceStatus.BLOCKED;
			}
			at = this.room.getEntAt(this.x, this.y + 1);
			if (at == null) {
				this.go(this.x, this.y + 1);
				return AdvanceStatus.MOVED;
			}
			break;
		}
		at.chgHP(-this.type.getAtk());
		return AdvanceStatus.ATTACKED;
	}
}
