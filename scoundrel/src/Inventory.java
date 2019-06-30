
/*
 * This class opens the player's items which can be accessed during battle. 
 */
import java.util.Scanner;

public class Inventory

{
	static Scanner in = new Scanner(System.in);

	static int potion = 2;
	static int super_potion = 1;
	static int star_drop = 2;
	static int cosmo_candy;
	static int blessed_nectar;
	static int money = 10;

	// |BYNAR'S WAS HERE|//
	public static Weapon wep = new Weapon("Stick", "Well... at least it's something.", 5, 10, 70);
	public static Item armour = new Item("Peasant's Garb", "Ooo, freshly washed!", 0, 0, 0, 0, 0);
	public static Item boots = new Item("Tattered Boots", "Keeps your feet somewhat dry.", 0, 0, 0, 0, 0);
	public static Item accessory = new Item("Teddy Bear", "Reminds you of childhood.", 0, 0, 0, 0, 0);

	public static void equipment() {
		System.out.println("*** EQUIPMENT ***");
		System.out.println("WEAPON: " + wep.name);
		System.out.println("	-" + wep.description);
		System.out.println("ARMOUR: " + armour.name);
		System.out.println("	-" + armour.description);
		System.out.println("FOOTWEAR: " + boots.name);
		System.out.println("	-" + boots.description);
		System.out.println("TRINKET: " + accessory.name);
		System.out.println("	-" + accessory.description);
		System.out.println();
		System.out.println("*** STATS ***");
		System.out.println(" Health: " + Fight.initialHP);
		System.out.println("   Mana: " + Fight.initialMP);
		System.out.println(" Attack: " + Fight.attack);
		System.out.println("Defense: " + Fight.defense);
		System.out.println("   Luck: " + Fight.luck);
		System.out.println("Agility: " + Fight.agility);
	}

	/**
	 * stores weapon data into inventory
	 * 
	 * @param inputWep - input weapon
	 */
	public static void addWep(Weapon inputWep) {
		System.out.println("______________________________");
		System.out.println();
		System.out.println("Equipped: " + wep.name);
		System.out.println("	-" + wep.description);
		System.out.println("New Item: " + inputWep.name);
		System.out.println("	-" + inputWep.description);
		System.out.println();
		System.out.println("Replace " + wep.name + " with " + inputWep.name + "? (y = yes, n = no)");

		boolean loop = true;
		while (loop) {

			String INPUT = in.nextLine();

			if (INPUT.matches("[a-z]+")) {// condition is met when the string only consists of letters
				INPUT = INPUT.toLowerCase();
				if (INPUT.equals("y")) {
					System.out.println("Equipped " + inputWep.name);
					wep = inputWep;// replaces current weapon with new weapon
					Fight.attack = (wep.maxdmg + wep.mindmg) / 2;
					loop = false;
				} else if (INPUT.equals("n")) {
					System.out.println("Discarded " + inputWep.name);
					loop = false;
				} else {
					System.out.println("(X)");
					System.out.println("");
				}
			} else {
				System.out.println("(X)");
				System.out.println("");
			} // else
		} // while
		System.out.println();
	}// addwep

	/**
	 * stores accessory data into inventory
	 * 
	 * @param inputArm - input armour
	 */
	public static void addArmour(Item inputArm) {
		System.out.println("______________________________");
		System.out.println();
		System.out.println("Equipped: " + armour.name);
		System.out.println("	-" + armour.description);
		System.out.println("New Item: " + inputArm.name);
		System.out.println("	-" + inputArm.description);
		System.out.println();
		System.out.println("Replace " + armour.name + " with " + inputArm.name + "? (y = yes, n = no)");

		boolean loop = true;
		while (loop) {

			String INPUT = in.nextLine();

			if (INPUT.matches("[a-z]+")) {// condition is met when the string only consists of letters
				INPUT = INPUT.toLowerCase();
				if (INPUT.equals("y")) {
					System.out.println("Equipped " + inputArm.name);
					// |UPDATING STATS|//
					Fight.initialHP -= armour.HP;// removing previous item bonuses
					Fight.initialMP -= armour.MP;
					Fight.defense -= armour.DEF;
					Fight.luck -= armour.LUCK;
					Fight.agility -= armour.AGL;

					Fight.initialHP += inputArm.HP;// replacing item bonuses
					Fight.initialMP += inputArm.MP;
					Fight.defense += inputArm.DEF;
					Fight.luck += inputArm.LUCK;
					Fight.agility += inputArm.AGL;
					// |REPLACING|//
					armour = inputArm;// replaces current armour with new armour
					loop = false;
				} else if (INPUT.equals("n")) {
					System.out.println("Discarded " + inputArm.name);
					loop = false;
				} else {
					System.out.println("(X)");
					System.out.println("");
				}
			} else {
				System.out.println("(X)");
				System.out.println("");
			} // else
		} // while
		System.out.println();
	}// addArmour

	/**
	 * stores accessory data into inventory
	 * 
	 * @param inputBoot - input boots
	 */
	public static void addBoots(Item inputBoot) {
		System.out.println("______________________________");
		System.out.println();
		System.out.println("Equipped: " + boots.name);
		System.out.println("	-" + boots.description);
		System.out.println("New Item: " + inputBoot.name);
		System.out.println("	-" + inputBoot.description);
		System.out.println();
		System.out.println("Replace " + boots.name + " with " + inputBoot.name + "? (y = yes, n = no)");

		boolean loop = true;
		while (loop) {

			String INPUT = in.nextLine();

			if (INPUT.matches("[a-z]+")) {// condition is met when the string only consists of letters
				INPUT = INPUT.toLowerCase();
				if (INPUT.equals("y")) {
					System.out.println("Equipped " + inputBoot.name);
					// |UPDATING STATS|//
					Fight.initialHP -= boots.HP;// removing previous item bonuses
					Fight.initialMP -= boots.MP;
					Fight.defense -= boots.DEF;
					Fight.luck -= boots.LUCK;
					Fight.agility -= boots.AGL;

					Fight.initialHP += inputBoot.HP;// replacing item bonuses
					Fight.initialMP += inputBoot.MP;
					Fight.defense += inputBoot.DEF;
					Fight.luck += inputBoot.LUCK;
					Fight.agility += inputBoot.AGL;
					// |REPLACING|//
					boots = inputBoot;// replaces current boots with new boots
					loop = false;
				} else if (INPUT.equals("n")) {
					System.out.println("Discarded " + inputBoot.name);
					loop = false;
				} else {
					System.out.println("(X)");
					System.out.println("");
				}
			} else {
				System.out.println("(X)");
				System.out.println("");
			} // else
		} // while
		System.out.println();
	}// addBoots

	/**
	 * stores accessory data into inventory
	 * 
	 * @param inputAcc - input accessory
	 */
	public static void addAccessory(Item inputAcc) {
		System.out.println("______________________________");
		System.out.println();
		System.out.println("Equipped: " + accessory.name);
		System.out.println("	-" + accessory.description);
		System.out.println("New Item: " + inputAcc.name);
		System.out.println("	-" + inputAcc.description);
		System.out.println();
		System.out.print("Replace " + accessory.name + " with " + inputAcc.name + "? (y = yes, n = no)");

		boolean loop = true;
		while (loop) {

			String INPUT = in.nextLine();

			if (INPUT.matches("[a-z]+")) {// condition is met when the string only consists of letters
				INPUT = INPUT.toLowerCase();
				if (INPUT.equals("y")) {
					System.out.println("Equipped " + inputAcc.name);
					// |UPDATING STATS|//
					Fight.initialHP -= accessory.HP;// removing previous item bonuses
					Fight.initialMP -= accessory.MP;
					Fight.defense -= accessory.DEF;
					Fight.luck -= accessory.LUCK;
					Fight.agility -= accessory.AGL;

					Fight.initialHP += inputAcc.HP;// replacing item bonuses
					Fight.initialMP += inputAcc.MP;
					Fight.defense += inputAcc.DEF;
					Fight.luck += inputAcc.LUCK;
					Fight.agility += inputAcc.AGL;
					// |REPLACING|//
					accessory = inputAcc;// replaces current accessory with new accessory
					loop = false;
				} else if (INPUT.equals("n")) {
					System.out.println("Discarded " + inputAcc.name);
					loop = false;
				} else {
					System.out.println("(X)");
					System.out.println("");
				}
			} else {
				System.out.println("(X)");
				System.out.println("");
			} // else
		} // while
		System.out.println();
	}// addAccessory
}// inventory
