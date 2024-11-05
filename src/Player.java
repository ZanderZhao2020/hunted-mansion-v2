import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
	public List<Item> inv;
	public Player(int x, int y, Room room) {
		super(x, y, room, Entity.Type.PLAYER);
		this.inv = new ArrayList<>();
		this.inv.add(new Item(Item.Type.STICK));
	}

	public Entity advance(Direction dir) {
		Entity at = null;
		switch (dir) {
		case LEFT:
			if (this.x <= 0) {
				return null;
			}
			at = this.room.ents[this.y][this.x - 1];
			if (at == null) {
				this.go(this.x - 1, this.y);
				return this;
			}
			break;
		case RIGHT:
			if (this.x >= this.room.width - 1) {
				return null;
			}
			at = this.room.ents[this.y][this.x + 1];
			if (at == null) {
				this.go(this.x + 1, this.y);
				return this;
			}
			break;
		case UP:
			if (this.y <= 0) {
				return null;
			}
			at = this.room.ents[this.y - 1][this.x];
			if (at == null) {
				this.go(this.x, this.y - 1);
				return this;
			}
			break;
		case DOWN:
			if (this.y >= this.room.height - 1) {
				return null;
			}
			at = this.room.ents[this.y + 1][this.x];
			if (at == null) {
				this.go(this.x, this.y + 1);
				return this;
			}
			break;
		}
		at.chgHP(-this.type.atk);
		return at;
	}
}
