import java.util.Scanner;

public class Shopkeep {
	static Scanner in = new Scanner(System.in);
	static Typewriter typer = new Typewriter();
	
	static boolean shopStart = true;
	static int purchaseType = 0;// 0 = weapon | 1 = item | 2 = consumables(potions idk) :P
	static int price = 0;

	static Weapon WEP = new Weapon("WEP NAME", "WEP DESCRIPT", 0, 0, 0);
	static Item ITEM = new Item("ITEM NAME", "ITEM DESCRIPT", 0, 0, 0, 0, 0);

	static int consumableType = 0;// 0 = Potion | 1 = Star Drop | 2 = Super Potion | 3 = Cosmo Candy | 4 = Blessed
									// Nectar
	static String consumableName = "CON NAME";

	public static void shop() {
		

		if (shopStart) {
			shopStart = false;
			System.out.println("==============================");
			System.out.println("WELCOME TO SHOPKEEP PATATO'S");
			System.out.println();
		}

		boolean loop = true;
		while (loop) {
			System.out.println("1) Weapons");
			System.out.println("2) Armour");
			System.out.println("3) Footwear");
			System.out.println("4) Trinkets");
			System.out.println("5) Items");
			System.out.println("6) EXIT");
			System.out.println("		Gold: $" + Inventory.money);
			System.out.print("What would you like to buy?");
			String INPUT = in.nextLine();

			if (INPUT.equals("carby")) {
				System.out.println();
				typer.type(
						"Shopkeeper Potato noticed your beard and decided to give you the best products that she sells!");
				System.out.println();

				WEP = new Weapon("Vampire Killer", "(55-60) A family-treasured weapon and punisher of evil", 55, 60,
						80);
				Inventory.addWep(WEP);
				ITEM = new Item("Diamond armor", "(HP:+100 DEF:+100) Basic armor.", 100, 0, 100, 0, 0);
				Inventory.addArmour(ITEM);
				ITEM = new Item("Hermes’ Slippers", "(AGL:+70) Crafted by the God himself.", 0, 0, 0, 0, 70);
				Inventory.addBoots(ITEM);
				ITEM = new Item("Hex Ring", "(MP:+80) A ring passed down by the strongest magicians.", 0, 80, 0, 0, 0);
				Inventory.addAccessory(ITEM);
				
				Inventory.potion = 50;
				Inventory.super_potion = 50;
				Inventory.star_drop = 50;
				Inventory.cosmo_candy = 50;
				Inventory.blessed_nectar = 50;
				
				World.delay(1000);
				
				loop = false;
				shopStart = true;
			}

			if (INPUT.matches("[1-6]+")) {// condition is met when the string only consists of numbers
				int input = Integer.parseInt(INPUT);

				if (input == 1) {
					System.out.println();
					wepList();
					break;
				} else if (input == 2) {
					System.out.println();
					armList();
					break;
				} else if (input == 3) {
					System.out.println();
					bootList();
					break;
				} else if (input == 4) {
					System.out.println();
					accList();
					break;
				} else if (input == 5) {
					System.out.println();
					itemList();
					break;
				} else if (input == 6) {
					loop = false;
					shopStart = true;
					
					typer.type("Shopkeeper Potato wishes you the best of luck in your adventure!");
					World.delay(2000);
				}
			} else {
				System.out.println("(X)");
				System.out.println();
			} // else
		} // while
	}// shop

	public static void purchase() {
		if (purchaseType == 0) {// class weapon objects
			boolean loop = true;
			while (loop) {
				System.out.print("Buy " + WEP.name + "? (y = yes, n = no)");
				String INPUT = in.nextLine();

				if (INPUT.matches("[yn]+")) {// condition is met when the string only consists of numbers
					if (INPUT.equals("y")) {// yes
						loop = false;
						System.out.println("Bought " + WEP.name);
						Inventory.money -= price;
						System.out.println();
						Inventory.addWep(WEP);
						shop();
					} else {// no
						shop();
						loop = false;
					}
				} else {
					System.out.println("(X)");
				} // else
			} // while
		} else if (purchaseType == 4) {
			boolean loop = true;
			while (loop) {
				System.out.print("Buy " + consumableName + "? (y = yes, n = no)");
				String INPUT = in.nextLine();

				if (INPUT.matches("[yn]+")) {// condition is met when the string only consists of numbers
					if (INPUT.equals("y")) {// yes
						loop = false;
						System.out.println("Bought " + consumableName);
						Inventory.money -= price;
						// 0 = Potion | 1 = Star Drop | 2 = Super Potion | 3 = Cosmo Candy | 4 = Blessed
						// Nectar
						if (consumableType == 0) {
							Inventory.potion += 1;
						}
						if (consumableType == 1) {
							Inventory.star_drop += 1;
						}
						if (consumableType == 2) {
							Inventory.super_potion += 1;
						}
						if (consumableType == 3) {
							Inventory.cosmo_candy += 1;
						}
						if (consumableType == 4) {
							Inventory.blessed_nectar += 1;
						}
						shop();
					} else {// no
						shop();
						loop = false;
					}
				} else {
					System.out.println("(X)");
				} // else
			} // while
		} else {// class Item objects
			boolean loop = true;
			while (loop) {
				System.out.print("Buy " + ITEM.name + "? (y = yes, n = no)");
				String INPUT = in.nextLine();

				if (INPUT.matches("[yn]+")) {// condition is met when the string only consists of numbers
					if (INPUT.equals("y")) {// yes
						loop = false;
						System.out.println("Bought " + ITEM.name);
						Inventory.money -= price;
						System.out.println();
						if (purchaseType == 1) {// buying armour
							Inventory.addArmour(ITEM);
						}
						if (purchaseType == 2) {// buying boots
							Inventory.addBoots(ITEM);
						}
						if (purchaseType == 3) {// buying accessory
							Inventory.addAccessory(ITEM);
						}
						shop();
					} else {// no
						shop();
						loop = false;
					}
				} else {
					System.out.println("(X)");
				} // else
			} // while
		}
	}

	public static void wepList() {

		System.out.println("1) Worn dagger ($15):");
		System.out.println("	-(10-14 DMG) A small blade, poor range.");
		System.out.println("2) Whip ($20):");
		System.out.println("	-(10-14 DMG) A leather whip, great range.");
		System.out.println("3) Wooden sword ($35):");
		System.out.println("	-(20-23 DMG) A basic sword, normal range.");
		System.out.println("4) Silver sabre ($50):");
		System.out.println("	-(30-32 DMG) A strong sword, normal range.");
		System.out.println("5) Flail ($50):");
		System.out.println("	-(40-45 DMG) A whip with a spiked ball, causes 40-45 damage, great range.");
		System.out.println("6) The Buster Sword ($96):");
		System.out.println(
				"	-(50-58 DMG) We didn’t steal this, a great sword wielded by a spiky haired mercenary, normal range.");
		System.out.println("7) The Vampire Killer ($100):");
		System.out.println(
				"	-(55-60 DMG) We also didn’t steal this, a family-treasured weapon and punisher of evil, great range.");
		System.out.println("8) BACK");
		
		boolean loop = true;
		while (loop) {
			System.out.println("		Gold: $" + Inventory.money);
			System.out.print("What would you like to buy?");
			String INPUT = in.nextLine();

			if (INPUT.matches("[1-8]+")) {// condition is met when the string only consists of numbers
				int input = Integer.parseInt(INPUT);
				boolean buy = false;

				if (input == 1) {
					System.out.println();
					WEP = new Weapon("Worn Dagger", "(10-14 DMG) A small blade.", 10, 14, 60);
					buy = true;
					price = 15;
				} else if (input == 2) {
					System.out.println();
					WEP = new Weapon("Whip", "(10-14 DMG) A leather whip.", 10, 14, 80);
					buy = true;
					price = 20;
				} else if (input == 3) {
					System.out.println();
					WEP = new Weapon("Wooden Sword", "(20-23 DMG) A basic sword.", 20, 23, 70);
					buy = true;
					price = 35;
				} else if (input == 4) {
					System.out.println();
					WEP = new Weapon("Silver Sabre", "(30-32 DMG) A strong sword.", 30, 32, 70);
					buy = true;
					price = 50;
				} else if (input == 5) {
					System.out.println();
					WEP = new Weapon("Flail", "(40-45 DMG) A whip with a spiked ball.", 40, 45, 80);
					buy = true;
					price = 50;
				} else if (input == 6) {
					System.out.println();
					WEP = new Weapon("Buster Sword", "(50-58 DMG) A great sword wielded by a spiky haired mercenary.",
							50, 58, 70);
					buy = true;
					price = 96;
				} else if (input == 7) {
					System.out.println();
					WEP = new Weapon("Vampire Killer", "(55-60) A family-treasured weapon and punisher of evil", 55, 60,
							80);
					buy = true;
					price = 100;
				} else if (input == 8) {
					shop();
					loop = false;
				}

				if (buy) {
					if (Inventory.money >= price) {
						purchaseType = 0;
						purchase();
						loop = false;
					} else {
						System.out.println("You don't have enough money.");
					}
				}
			} else {
				System.out.println("(X)");
			} // else
		} // while
	}// wepList

	public static void armList() {

		System.out.println("1) Leather armor ($30):");
		System.out.println("	-Basic armor, boosts Defense by 10 and HP by 20.");
		System.out.println("2) Silver armor ($60):");
		System.out.println("	-Shining armor, boosts Defense by 45 and HP by 50.");
		System.out.println("3) Gold armor ($100):");
		System.out.println("	-Strong armor, boosts Defense by 60. and HP by 75.");
		System.out.println("4) Diamond armor ($170):");
		System.out.println("	-Invincible armor, boosts Defense by 100 and HP by 100.");
		System.out.println("5) BACK");

		boolean loop = true;
		while (loop) {
			System.out.println("		Gold: $" + Inventory.money);
			System.out.print("What would you like to buy?");
			String INPUT = in.nextLine();

			if (INPUT.matches("[1-5]+")) {// condition is met when the string only consists of numbers
				int input = Integer.parseInt(INPUT);
				boolean buy = false;

				if (input == 1) {
					System.out.println();
					ITEM = new Item("Leather Armour", "(HP:+20 DEF:+10) Basic armor.", 20, 0, 10, 0, 0);
					buy = true;
					price = 30;
				} else if (input == 2) {
					System.out.println();
					ITEM = new Item("Silver armor", "(HP:+50 DEF:+45) Basic armor.", 50, 0, 45, 0, 0);
					buy = true;
					price = 60;
				} else if (input == 3) {
					System.out.println();
					ITEM = new Item("Gold armor", "(HP:+75 DEF:+60) Basic armor.", 75, 0, 60, 0, 0);
					buy = true;
					price = 100;
				} else if (input == 4) {
					System.out.println();
					ITEM = new Item("Diamond armor", "(HP:+100 DEF:+100) Basic armor.", 100, 0, 100, 0, 0);
					buy = true;
					price = 170;
				} else if (input == 5) {
					shop();
					loop = false;
				}

				if (buy) {
					if (Inventory.money >= price) {
						purchaseType = 1;
						purchase();
						loop = false;
					} else {
						System.out.println("You don't have enough money.");
					}
				}
			} else {
				System.out.println("(X)");
			} // else
		} // while
	}// armList

	public static void bootList() {

		System.out.println("1) Basic Runners ($20):");
		System.out.println("	-Decent runners, boosts Agility by 10.");
		System.out.println("2) Adventurer’s Boots ($45):");
		System.out.println("	-Good for running in harsh terrain, boosts Agility by 30.");
		System.out.println("3) Hermes’ Slippers ($90):");
		System.out.println("	-Crafted by the God himself, boosts Agility by 70.");
		System.out.println("4) BACK");

		boolean loop = true;
		while (loop) {
			System.out.println("		Gold: $" + Inventory.money);
			System.out.print("What would you like to buy?");
			String INPUT = in.nextLine();

			if (INPUT.matches("[1-4]+")) {// condition is met when the string only consists of numbers
				int input = Integer.parseInt(INPUT);
				boolean buy = false;

				if (input == 1) {
					System.out.println();
					ITEM = new Item("Basic Runners", "(AGL:+10) Decent runners.", 0, 0, 0, 0, 10);
					buy = true;
					price = 20;
				} else if (input == 2) {
					System.out.println();
					ITEM = new Item("Adventurer’s Boots", "(AGL:+30) Good for running in harsh terrain.", 0, 0, 0, 0,
							30);
					buy = true;
					price = 45;
				} else if (input == 3) {
					System.out.println();
					ITEM = new Item("Hermes’ Slippers", "(AGL:+70) Crafted by the God himself.", 0, 0, 0, 0, 70);
					buy = true;
					price = 90;
				} else if (input == 4) {
					shop();
					loop = false;
				}

				if (buy) {
					if (Inventory.money >= price) {
						purchaseType = 2;
						purchase();
						loop = false;
					} else {
						System.out.println("You don't have enough money.");
					}
				}
			} else {
				System.out.println("(X)");
			} // else
		} // while
	}// bootList

	public static void accList() {

		System.out.println("1) Jaded Ring ($20):");
		System.out.println("	-A worn out magical ring, boosts MP by 10.");
		System.out.println("2) Gemstone Ring ($35):");
		System.out.println("	-A basic magical ring, boosts MP by 30.");
		System.out.println("3) Rabbit’s Foot ($50):");
		System.out.println("	-Put it in your pocket for good luck, boosts Luck by 10.");
		System.out.println("4) Crystal Ring ($65):");
		System.out.println("	-A ring embedded with strong, magical potential, boosts MP by 50.");
		System.out.println("5) Hex Ring ($90):");
		System.out.println("	-A ring passed down by the strongest magicians, boosts MP by 80.");
		System.out.println("6) BACK");

		boolean loop = true;
		while (loop) {
			System.out.println("		Gold: $" + Inventory.money);
			System.out.print("What would you like to buy?");
			String INPUT = in.nextLine();
			boolean buy = false;

			if (INPUT.matches("[1-6]+")) {// condition is met when the string only consists of numbers
				int input = Integer.parseInt(INPUT);

				if (input == 1) {
					System.out.println();
					ITEM = new Item("Jaded Ring", "(MP:+10) A worn out magical ring.", 0, 10, 0, 0, 0);
					buy = true;
					price = 20;
				} else if (input == 2) {
					System.out.println();
					ITEM = new Item("Gemstone Ring", "(MP:+30) A basic magical ring.", 0, 30, 0, 0, 0);
					buy = true;
					price = 35;
				} else if (input == 3) {
					System.out.println();
					ITEM = new Item("Rabbit's Foot", "(LUC:+10) Put it in your pocket for good luck.", 0, 0, 0, 10, 0);
					buy = true;
					price = 50;
				} else if (input == 4) {
					System.out.println();
					ITEM = new Item("Crystal Ring", "(MP:+50) A ring embedded with strong, magical potential.", 0, 50,
							0, 0, 0);
					buy = true;
					price = 65;
				} else if (input == 5) {
					System.out.println();
					ITEM = new Item("Hex Ring", "(MP:+80) A ring passed down by the strongest magicians.", 0, 80, 0, 0,
							0);
					buy = true;
					price = 90;
				} else if (input == 6) {
					shop();
					loop = false;
				}

				if (buy) {
					if (Inventory.money >= price) {
						purchaseType = 3;
						purchase();
						loop = false;
					} else {
						System.out.println("You don't have enough money.");
					}
				}
			} else {
				System.out.println("(X)");
			} // else
		} // while
	}// accList

	public static void itemList() {

		System.out.println("1) Potion ($5):");
		System.out.println("	-Recovers 25 HP. Tastes like refreshing fruit punch.");
		System.out.println("2) Star Drop ($5):");
		System.out.println("	-Recovers 25 MP. It's a star-shaped chocolate.");
		System.out.println("3) Super Potion ($15):");
		System.out.println("	-Replenishes all HP. Tastes like warm, cozy mocha.");
		System.out.println("4) Cosmo Candy ($17):");
		System.out.println("	-Replenishes all MP. It's cotton candy with galaxy hues.");
		System.out.println("5) Blessed Nectar ($45):");
		System.out.println("	-Replenishes all HP and MP. It's a scrumptous, glowing honey comb.");
		System.out.println("6) BACK");

		boolean loop = true;
		while (loop) {
			System.out.println("		Gold: $" + Inventory.money);
			System.out.print("What would you like to buy?");
			String INPUT = in.nextLine();
			boolean buy = false;

			if (INPUT.matches("[1-6]+")) {// condition is met when the string only consists of numbers
				int input = Integer.parseInt(INPUT);

				if (input == 1) {
					System.out.println();
					consumableType = 0;
					consumableName = "Potion";
					buy = true;
					price = 5;
				} else if (input == 2) {
					System.out.println();
					consumableType = 1;
					consumableName = "Star Drop";
					buy = true;
					price = 5;
				} else if (input == 3) {
					System.out.println();
					consumableType = 2;
					consumableName = "Super Potion";
					buy = true;
					price = 15;
				} else if (input == 4) {
					System.out.println();
					consumableType = 3;
					consumableName = "Cosmo Candy";
					buy = true;
					price = 17;
				} else if (input == 5) {
					System.out.println();
					consumableType = 4;
					consumableName = "Blessed Nectar";
					buy = true;
					price = 45;
				} else if (input == 6) {
					shop();
					loop = false;
				}

				if (buy) {
					if (Inventory.money >= price) {
						purchaseType = 4;
						purchase();
						loop = false;
					} else {
						System.out.println("You don't have enough money.");
					}
				}
			} else {
				System.out.println("(X)");
			} // else
		} // while
	}// itemList
}
