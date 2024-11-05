
public class Item {
	public static abstract class Type {
		public static final Type STICK = new WeaponType("Stick", "Weak weapon.", 10);
		public static final Type SWORD = new WeaponType("Sword", "Strong weapon.", 40);
		public static final Type SMALL_HEAL_POTION = new ConsumableType("Small Heal Potion",
				"Regenerates a small amount of health", 50);
		public static final Type HEAL_POTION = new ConsumableType("Heal Potion", "Regenerates all your health.", 100);

		public String name, desc;

		public Type(String name, String desc) {
			this.name = name;
			this.desc = desc;
		}
	}

	public static class WeaponType extends Type {
		int dmg;

		public WeaponType(String name, String desc, int dmg) {
			super(name, desc);
			this.dmg = dmg;
		}
	}

	public static class ConsumableType extends Type {
		int heal;

		public ConsumableType(String name, String desc, int heal) {
			super(name, desc);
			this.heal = heal;
		}
	}

	public Type type;

	public Item(Type type) {
		this.type = type;
	}
}
