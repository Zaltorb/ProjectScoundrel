
public class Item {
	String name;
	String description;
	int HP;
	int MP;
	int DEF;
	int AGL;
	int LUCK;

	/**
	 * @param itemName
	 * @param descript
	 * @param hp
	 * @param mp
	 * @param def
	 * @param luck
	 * @param agil
	 */
	public Item(String itemName, String descript, int hp, int mp, int def, int luck, int agil) {
		name = itemName;
		description = descript;
		HP = hp;
		MP = mp;
		DEF = def;
		LUCK = luck;
		AGL = agil;
	}// Item
}// Item
