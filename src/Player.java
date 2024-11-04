
public class Player extends Entity {
	public static final char MAP_CHAR = '@';

	public Player(int x, int y, Room room) {
		super(x, y, room);
		this.hp = 100;
		room.setMapAt(x, y, Player.MAP_CHAR);
	}

	public void advance(Direction dir) {
		
	}
}
