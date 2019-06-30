
public class Player {
	public static int playerRow;//
	public static int playerColumn;//
	
	static Typewriter typer = new Typewriter();
	
	/**
	 * Find the player within the lvl, used after a new level is generated.
	 */
	public static void findPlayer() {
		for (int row = 0; row < LevelGenerator.map.length; row++)// scans through map array to find player position
		{
			for (int column = 0; column < LevelGenerator.map.length; column++) {
				if (LevelGenerator.map[row][column] == "@") {
					playerRow = row;
					playerColumn = column;
				}
			} // for
		} // for
	}

	/**
	 * Movement for the character will be using WASD. Indicate the direction and
	 * press enter.
	 */
	public static void movement(String direction) {
		boolean mob = false;
		boolean chest = false;
		boolean exit = false;
		boolean pitfall = false;
		
		int desireRow = playerRow;
		int desireColumn = playerColumn;
		if (direction.equals("w")) {
			desireRow = playerRow - 1;
		} else if (direction.equals("s")) {
			desireRow = playerRow + 1;
		} else if (direction.equals("a")) {
			desireColumn = playerColumn - 1;
		} else if (direction.equals("d")) {
			desireColumn = playerColumn + 1;
		} else {
			System.out.println();
		}

		if (LevelGenerator.mobPlotMAP[desireRow][desireColumn] == true) {// mob
			mob = true;
		} else if (LevelGenerator.map[desireRow][desireColumn] == "H") {// chest
			chest = true;
		} else if (LevelGenerator.map[desireRow][desireColumn] == "X") {// exit
			exit = true;
		} else if (LevelGenerator.map[desireRow][desireColumn] == " ") {// pitfall
			pitfall = true;
		}

		if (LevelGenerator.map[desireRow][desireColumn] == "." || LevelGenerator.map[desireRow][desireColumn] == "H"
				|| LevelGenerator.map[desireRow][desireColumn] == "X") {
			LevelGenerator.map[desireRow][desireColumn] = "@";
			LevelGenerator.map[playerRow][playerColumn] = ".";
			playerRow = desireRow;
			playerColumn = desireColumn;
		} else if (LevelGenerator.map[desireRow][desireColumn] == " ") {
			LevelGenerator.map[playerRow][playerColumn] = ".";
		}

		LevelGenerator.printMap();

		if (mob) { // MOB ENCOUNTER
			encounter();
		}
		if (chest) { // OPEN CHEST
			chestInteract();
		}
		if (exit) { // NEXT LVL
			World.nextLvl();
		}
		if (pitfall) {
			pitDeath();
		}
	}// movement

	/**
	 * An enemy has been encountered
	 */
	public static void encounter() {
		Control.actionloop = false;

		Fight.HP = Fight.initialHP + Fight.defense;
		Fight.MP = Fight.initialMP;

		boolean loop = true;
		while (loop) {
			int mob = (int) (Math.random() * 3);

			if (mob == 0) {
				loop = false;
				Enemy.newEnemy("Slime");
			} else if (mob == 1 && World.lvlCount >= 4) {
				loop = false;
				Enemy.newEnemy("Skeleton Soldier");
			} else if (mob == 2 && World.lvlCount >= 7) {
				loop = false;
				Enemy.newEnemy("Wraith");

			}
		}
		System.out.println("A " + Enemy.NAME + " has appeared!");
		System.out.println("*** " + Enemy.NAME + " STATS ***");
		System.out.println("     HP: " + Enemy.HP);
		System.out.println("Defense: " + Enemy.DEFENSE);
		System.out.println(" Attack: " + Enemy.ATTACK);
		System.out.println("   Luck: " + Enemy.LUCK);
		System.out.println("Agility: " + Enemy.AGILITY);
		System.out.println();
		Fight.combat();
	}// encounter

	public static void chestInteract() {
		Weapon WEP = new Weapon("WEP NAME", "WEP DESCRIPT", 0, 0, 0);
		Item ITEM = new Item("ITEM NAME", "ITEM DESCRIPT", 0, 0, 0, 0, 0);

		int rngArm = 0;
		int rngDrop = (int) (Math.random() * 100) + 1;

		if (rngDrop <= 5) {// weapon
			int rngWep = (int) (Math.random() * 5) + 1;// rng that picks an item

			if (rngWep == 1) {
				WEP = new Weapon("Worn Dagger", "(10-14 DMG)", 10, 14, 60);
			}
			if (rngWep == 2) {
				WEP = new Weapon("Whip", "(10-14 DMG)", 10, 14, 80);
			}
			if (rngWep == 3) {
				WEP = new Weapon("Wooden Sword", "(20-23 DMG)", 20, 23, 70);
			}
			if (rngWep == 4) {
				WEP = new Weapon("Silver Sabre", "(30-32 DMG)", 30, 32, 70);
			}
			if (rngWep == 5) {
				WEP = new Weapon("Flail", "(40-45 DMG)", 40, 45, 80);
			}
		} else if (rngDrop > 5 && rngDrop <= 10) {// armour
			rngArm = (int) (Math.random() * 4) + 1;// rng that picks an item

			if (rngArm == 1) {
				ITEM = new Item("Leather Armour", "(HP:+20 DEF:+10) Basic armor.", 20, 0, 10, 0, 0);
			}
			if (rngArm == 2) {
				ITEM = new Item("Basic Runners", "(AGL:+10) Decent runners.", 0, 0, 0, 0, 10);
			}
			if (rngArm == 3) {
				ITEM = new Item("Adventurer’s Boots", "(AGL:+30) Good for running in harsh terrain.", 0, 0, 0, 0, 30);
			}
			if (rngArm == 4) {
				ITEM = new Item("Hermes’ Slippers", "(AGL:+70) Crafted by the God himself.", 0, 0, 0, 0, 70);
			}
		} else if (rngDrop > 10 && rngDrop <= 30) {// accessories
			int rngAcc = (int) (Math.random() * 4) + 1;// rng that picks an item

			if (rngAcc == 1) {
				ITEM = new Item("Jaded Ring", "(MP:+10) A worn out magical ring.", 0, 10, 0, 0, 0);
			}
			if (rngAcc == 2) {
				ITEM = new Item("Gemstone Ring", "(MP:+30) A basic magical ring.", 0, 30, 0, 0, 0);
			}
			if (rngAcc == 3) {
				ITEM = new Item("Rabbit's Foot", "(LUC:+10) Put it in your pocket for good luck.", 0, 0, 0, 10, 0);
			}
			if (rngAcc == 4) {
				ITEM = new Item("Crystal Ring", "(MP:+50) A ring embedded with strong, magical potential.", 0, 50, 0, 0,
						0);
			}
		} else if (rngDrop > 30) {// money
			int rngCash = (int) (Math.random() * 50) + 10;
			Inventory.money += rngCash;// adds money into player class
			System.out.println("You've obtained $" + rngCash + "!");
			System.out.println();
		}

		// |PRINTING|//
		if (rngDrop <= 5) {// weapon
			System.out.println("You've obtained " + WEP.name + "!");
			System.out.println();
			Inventory.addWep(WEP);
		} else if (rngDrop > 5 && rngDrop <= 10) {// armour
			if (rngArm == 1) {// armour
				System.out.println("You've obtained " + ITEM.name + "!");
				System.out.println();
				Inventory.addArmour(ITEM);
			} else {// boots
				System.out.println("You've obtained " + ITEM.name + "!");
				System.out.println();
				Inventory.addBoots(ITEM);
			}
		} else if (rngDrop > 10 && rngDrop <= 30) {// accessories
			System.out.println("You've obtained " + ITEM.name + "!");
			System.out.println();
			Inventory.addAccessory(ITEM);
		}
	}// chestInteract
	
	public static void pitDeath() {
		typer.type("You did not watch your footing and have slipped into the pit!");
		typer.type("As you fall, you see the light above you grow dimmer...");
		typer.type("You're a terrible scoundrel. You were not made for adventuring.");
		typer.type("Sleep, and enjoy your eternal slumber in this dungeon.");
		World.delay(5000);
		System.exit(0);
	}
}// player
