
public abstract class Item {
	enum Type {
		SWORD("Sword", "Bladed weapon."), POTION("Potion", "Heals you.");

		private String name, desc;

		private Type(String name, String desc) {
			this.name = name;
			this.desc = desc;
		}
	}
}
