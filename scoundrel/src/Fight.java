import java.util.Scanner;

public class Fight

{
	static int HP = 70;
	static int initialHP = 70; // initial HP is the full HP, int 'HP' will be manipulated throughout the
								// battle.
	static int MP = 70;
	static int initialMP = 70; // initial MP is the full MP, int 'MP' will be manipulated throughout the
								// battle.
	static int defense = 5;
	static int attack = 10;
	static int luck = 35;
	static int agility = 7;

	static Scanner in = new Scanner(System.in);
	static Typewriter typer = new Typewriter();

	/**
	 * This method is able to analyze both the monster's and player's stats during
	 * battle.
	 */
	public static void Analyze() {
		System.out.println("***  PLAYER | " + Enemy.NAME + "  ***");
		System.out.println(" Health: " + initialHP + " | " + Enemy.initialHP);
		System.out.println(" Attack: " + attack + " | " + Enemy.ATTACK);
		System.out.println("Defense: " + defense + " | " + Enemy.DEFENSE);
		System.out.println("   Luck: " + luck + " | " + Enemy.LUCK);
		System.out.println("Agility: " + agility + " | " + Enemy.AGILITY);
	}

// | BRYAN'S CORNER | //

	public static void combat() {
		String INPUT = null;
		int input = 0;
		boolean inputLoop = true;
		int outcome = 3; // 0 = player dead | 1 = mob dead | 2 = flee

		boolean combatLoop = true;
		back: while (combatLoop) {
			System.out.println("____________________________________________________________");
			System.out.println("Player HP: " + HP + "(+" + defense + " DEF)/" + initialHP + " | " + Enemy.NAME + " HP: "
					+ Enemy.HP + "/" + Enemy.initialHP);
			System.out.println();
			System.out.println("1: ANALYZE");
			System.out.println("2: ATTACK");
			System.out.println("3: ITEMS");
			System.out.println("4: RUN");
			System.out.print("Input... ");

			inputLoop = true;
			while (inputLoop) {
				INPUT = in.nextLine();

				if (INPUT.equals("carby")) {
					System.out.println();
					typer.type("the enemy spontaneously combusted due to the flowing beard you miraculously grew!");
					System.out.println();
					Enemy.HP -= Enemy.HP;
					outcome = 1;
					break back;
				}
				if (INPUT.matches("[1234]+") && INPUT.length() == 1) {
					input = Integer.parseInt(INPUT);
					if (Enemy.fighting_banshee && input == 4) {
						System.out.println("You can not escape the Banshee's presence...");
					} else {
						inputLoop = false;
					}
				} else {
					System.out.println("(X)");
				}
			} // while(inputLoop)

			System.out.println();

			if (input == 1) { // analyze the enemy, as well as see your own stats
				Analyze();
				System.out.println();
				
				continue; // goes to the beginning of loop
			} else if (input == 2) { // attack the enemy
				System.out.println("*** ATTACKS ***");
				System.out.println("1: USE " + Inventory.wep.name);
				System.out.println("2: USE MAGIC");
				System.out.println("3: BACK");
				System.out.print("Input... ");

				inputLoop = true;
				while (inputLoop) {
					INPUT = in.nextLine();

					if (INPUT.matches("[123]+") && INPUT.length() == 1) {
						input = Integer.parseInt(INPUT);
						inputLoop = false;
					} else {
						System.out.println("(X)");
					}
				} // while(inputLoop)

				System.out.println();

				if (input == 1) {// WEAPON
					wep();
				} else if (input == 2) {// MAGIC
					System.out.println("*** MAGIC ATTACKS ***");
					System.out.println(
							"HP: " + HP + "(+" + defense + " DEF)/" + initialHP + " | MP: " + MP + "/" + initialMP);
					System.out.println();
					System.out.println("1: FIRE");
					System.out.println("	-(15 MP) An attack where the user conjures a ball of fire.");
					System.out.println("2: ICE");
					System.out.println(
							"	-(17 MP) An icy mist that gives the enemy frostbite. Will have the chance to freeze");
					System.out.println("	 an enemy (enemy misses a turn).");
					System.out.println("3: HEAL");
					System.out.println("	-(30 MP) Recovers 50 HP.");
					System.out.println("4: BACK");
					System.out.print("Input... ");

					inputLoop = true;
					while (inputLoop) {
						INPUT = in.nextLine();

						if (INPUT.matches("[1234]+") && INPUT.length() == 1) {
							input = Integer.parseInt(INPUT);

							// MP check
							if (input == 1) {
								if (MP == 0 || MP < 15) {// insufficient MP and cannot use this move
									System.out.println("You have insufficient MP! You can't use this move!");
								} else {
									inputLoop = false;
								}
							} else if (input == 2) {
								if (MP == 0 || MP < 17) {// insufficient MP and cannot use this move
									System.out.println("You have insufficient MP! You can't use this move!");
								} else {
									inputLoop = false;
								}
							} else if (input == 3) {
								if (MP == 0 || MP < 30) // insufficient MP and cannot use this move
								{
									System.out.println("You have insufficient MP! You can't use this move!");
								} else {
									inputLoop = false;
								}
							} else if (input == 4) {
								inputLoop = false;
							}
						} else {
							System.out.println("(X)");
						}
					} // while(inputLoop)

					System.out.println();

					if (input == 1) {// fire
						fire();
					} else if (input == 2) {// ice
						ice();
					} else if (input == 3) {// heal
						if (HP + 50 > initialHP) {
							HP += initialHP - HP;
						} else {
							HP += 50;
						}
						System.out.println("Your HP has been restored.");
					} else if (input == 4) {// MAGIC back
						continue; // goes to the beginning of loop
					}
				} else if (input == 3) {// ATTACK back
					continue; // goes to the beginning of loop
				}
			} else if (input == 3) { // ITEMS
				System.out.println("*** INVENTORY ***");
				System.out.println(
						"HP: " + HP + "(+" + defense + " DEF)/" + initialHP + " | MP: " + MP + "/" + initialMP);
				System.out.println();
				System.out.println("1: Potion (x" + Inventory.potion + ")");
				System.out.println("	-Recovers 25 HP. Tastes like refreshing fruit punch.");
				System.out.println("2: Super Potion (x" + Inventory.super_potion + ")");
				System.out.println("	-Replenishes all HP. Tastes like warm, cozy mocha.");
				System.out.println("3: Star Drop (x" + Inventory.star_drop + ")");
				System.out.println("	-Recovers 25 MP. It's a star-shaped chocolate.");
				System.out.println("4: Cosmo Candy (x" + Inventory.cosmo_candy + ")");
				System.out.println("	-Replenishes all MP. It's cotton candy with galaxy hues.");
				System.out.println("5: Blessed Nectar (x" + Inventory.blessed_nectar + ")");
				System.out.println("	-Replenishes all HP and MP. It's a scrumptous, glowing honey comb.");
				System.out.println("6: BACK");
				System.out.print("Input... ");

				inputLoop = true;
				while (inputLoop) {
					INPUT = in.nextLine();

					if (INPUT.matches("[123456]+") && INPUT.length() == 1) {
						input = Integer.parseInt(INPUT);

						if (input == 1 && Inventory.potion < 1) {
							System.out.println("You are out of this item!");
						} else if (input == 2 && Inventory.super_potion < 1) {
							System.out.println("You are out of this item!");
						} else if (input == 3 && Inventory.star_drop < 1) {
							System.out.println("You are out of this item!");
						} else if (input == 4 && Inventory.cosmo_candy < 1) {
							System.out.println("You are out of this item!");
						} else if (input == 5 && Inventory.blessed_nectar < 1) {
							System.out.println("You are out of this item!");
						} else {
							inputLoop = false;
						}
					} else {
						System.out.println("(X)");
					}
				} // while(inputLoop)

				if (input == 1) { // Potion
					Inventory.potion -= 1;
					if (HP + 25 > initialHP) {
						HP += initialHP - HP;
					} else {
						HP += 25;
					}
					System.out.println("Your HP has been restored.");
				} else if (input == 2) { // Super Potion
					Inventory.super_potion -= 1;
					HP = initialHP;
					System.out.println("Your HP has been restored.");
				} else if (input == 3) { // Star Drop
					Inventory.star_drop -= 1;
					if (MP + 25 > initialMP) {
						MP += initialMP - MP;
					} else {
						MP += 25;
					}
					System.out.println("Your MP has been restored.");
				} else if (input == 4) { // Cosmo Candy
					Inventory.cosmo_candy -= 1;
					MP = initialMP;
					System.out.println("Your MP has been restored.");
				} else if (input == 5) { // Blessed Nectar
					Inventory.blessed_nectar -= 1;
					System.out.println("Your HP and/or MP has been restored.");
					HP = initialHP;
					MP = initialMP;
				} else if (input == 6) { // INVENTORY BACK
					continue; // goes to the beginning of loop
				}
			} else if (input == 4) { // FLEE
				System.out.println("Player AGL: " + agility + " | " + Enemy.NAME + " AGL: " + Enemy.AGILITY);
				typer.type("The enemy is very keen on fighting, and refuses you to run.");
				System.out.print("Attempt to flee anyways? (y or n) ");

				inputLoop = true;
				while (inputLoop) {
					INPUT = in.nextLine();
					INPUT = INPUT.toLowerCase();
					if (INPUT.matches("[yn]+") && INPUT.length() == 1) {
						inputLoop = false;
					} else {
						System.out.println("(X)");
					}
				} // while(inputLoop)

				System.out.println();

				if (INPUT.equals("y")) {
					typer.slowType("...");

					boolean flee = flee();
					if (flee) {// successfully fled
						outcome = 2;
						break back;
					} else {// failed to flee
						Enemy.fleeRage();
					}
				} else if (INPUT.equals("n")) {// FLEE BACK
					continue; // goes to the beginning of loop
				}
			}

			// CHECK FOR ENEMY DEATH
			if (Enemy.HP <= 0) { // mob is dead when hp = 0
				outcome = 1;
				break back;
			}

			// ENEMY'S TURN
			if (!freeze) {
				Enemy.attack();
			} else if (freeze) {
				freeze = false;
			}

			// CHECK FOR PLAYER DEATH
			if (HP <= 0) { // player is dead when hp = 0
				outcome = 0;
				break back;
			}
		} // back: while (combatLoop)

		if (outcome == 0) {// PLAYER DIED
			gameOver();
		} else if (outcome == 1) {// ENEMY DIED
			if (Enemy.fighting_banshee) {
				Enemy.fighting_banshee = false;
				bansheeWin();
			} else {
				Enemy.fighting_slime = false;
				Enemy.fighting_skeleton = false;
				Enemy.fighting_wraith = false;

				Inventory.money += Enemy.cash_drop;

				typer.type("The " + Enemy.NAME + " has been defeated!");
				System.out.println("You have earned $" + Enemy.cash_drop + "!");
			}
			World.delay(3000);

			LevelGenerator.mobPlotMAP[Player.playerRow][Player.playerColumn] = false;
			LevelGenerator.printMap();
			Control.actionloop = true;
			Control.keyInput();
		} else if (outcome == 2) {// PLAYER FLED
			typer.type("Your attempt to flee has succeeded! You have escaped the " + Enemy.NAME + "!");			
			World.delay(3000);

			LevelGenerator.mobPlotMAP[Player.playerRow][Player.playerColumn] = false;
			LevelGenerator.printMap();
			Control.actionloop = true;
			Control.keyInput();
		}
	}// combat

	// |ENEMY TURN| //
	public static void enemyAttack() {
		boolean hit = Inventory.wep.hitOrMiss();
		if (hit) { // enemy landed attack
			boolean playerDodge = playerDodge();
			if (!playerDodge) {// player failed dodge
				Enemy.attack();
				System.out.println();
			} else {// player dodged
				typer.type("The " + Enemy.NAME + " goes in for the attack, but you evaded!");
			}
		} else { // enemy failed to land attack
			typer.type("The " + Enemy.NAME + " attempts to land a hit, but to no avail!");
		}
	}

	public static boolean playerDodge() {
		boolean dodged = false;

		if (agility < 50) {
			int dodge_chance = (int) (Math.random() * (4) + 1);
			if (dodge_chance == 1) {
				dodged = true;
			}
		} else if (agility >= 50 && agility <= 75) {
			int dodge_chance = (int) (Math.random() * (2) + 1);
			if (dodge_chance == 1) {
				dodged = true;
			}
		} else if (agility > 75) {
			int dodge_chance = (int) (Math.random() * (4) + 1);
			if (dodge_chance <= 3) {
				dodged = true;
			}
		}

		return dodged;
	}// playerDodge()

	// |PLAYER TURN| //
	public static void wep() {
		int DMG = Inventory.wep.DMG();
		int critChance = (int) (Math.random() * 100 + 1);

		boolean hit = Inventory.wep.hitOrMiss();
		if (hit) { // player landed attack
			boolean enemyDodge = Enemy.dodge();
			if (!enemyDodge) {// enemy failed dodge
				if (critChance <= luck) {
					DMG *= 2;
					typer.type("You attacked the " + Enemy.NAME + " with great strength! The " + Enemy.NAME + " lost "
							+ DMG + " HP!");
				} else {
					typer.type("You attacked the " + Enemy.NAME + "! The " + Enemy.NAME + " lost " + DMG + " HP!");
				}
				Enemy.HP -= DMG;
			} else {// enemy dodged
				typer.type("You lash out at " + Enemy.NAME + ", but they dodged your attack!");
			}
		} else { // player failed to land attack
			typer.type("You swing your weapon, but to no avail!");
		}
	}// wep()

	public static void fire() {
		MP -= 15;
		int fire_attack = (int) (Math.random() * (25 - 20) + 20);
		typer.type("You burned the enemy! The enemy lost " + fire_attack + " HP!");

		Enemy.HP -= fire_attack;
	}// fire

	static boolean freeze = false;

	public static void ice() {
		MP -= 15;
		int frozen = (int) (Math.random() * (5 - 1) + 1);
		int ice_attack = (int) (Math.random() * (25 - 20) + 20);

		typer.type("You freezed the enemy! The enemy lost " + ice_attack + " HP!");
		Enemy.HP -= ice_attack;

		if (frozen < 2) { // inflicts frozen enemy, enemy misses a turn
			typer.type("The enemy is frozen! It will take a turn to thaw itself out.");
			freeze = true;
		}
	}// ice()

	public static boolean flee() {
		boolean flee = false;

		int scale_factor = (100 / agility);

		int player_percentage = (agility * scale_factor);
		int enemy_percentage = (scale_factor * Enemy.AGILITY);

		int flee_chance = (player_percentage - enemy_percentage);
		if (flee_chance == 0) { // 50/50 chance of escape

		} else { // chance of escape is greater that 0%
			int chance = (int) (Math.random() * 100 + 1);

			if (chance <= flee_chance) {
				flee = true;
			}
		}

		return flee;
	}// flee

// | POST COMBAT | //

	/**
	 * If your HP is at 0, and asks if you want to try again
	 */
	public static void gameOver() {
		typer.type("NO! The last attack has brutally killed you!");
		typer.type("You take your last dying breath, the enemy hovering over you and snickering deviously...");
		typer.type("You're a terrible scoundrel. You were not made for adventuring.");
		typer.type("Sleep, and enjoy your eternal slumber in this dungeon.");
		World.delay(5000);
		System.exit(0);
	}// gameOver()

	public static void bansheeWin() { // after a successful battle, the player earns cash + map is reprinted
		Weapon WEP = new Weapon("WEP NAME", "WEP DESCRIPT", 0, 0, 0);
		Item ITEM = new Item("ITEM NAME", "ITEM DESCRIPT", 0, 0, 0, 0, 0);

		int rngArm = 0;
		int rngDrop = (int) (Math.random() * 100) + 1;

		if (rngDrop <= 5) {// weapon
			int rngWep = (int) (Math.random() * 3) + 1;// rng that picks an item

			if (rngWep == 1) {
				WEP = new Weapon("Flail", "(40-45 DMG)", 40, 45, 80);
			}
			if (rngWep == 2) {
				WEP = new Weapon("Buster Sword", "(50-58 DMG) A great sword wielded by a spiky haired mercenary.", 50,
						58, 70);
			}
			if (rngWep == 3) {
				WEP = new Weapon("Vampire Killer", "(55-60) A family-treasured weapon and punisher of evil", 55, 60,
						80);
			}
		} else if (rngDrop > 5 && rngDrop <= 10) {// armour
			rngArm = (int) (Math.random() * 3) + 1;// rng that picks an item

			if (rngArm == 1) {
				ITEM = new Item("Silver armor", "(HP:+50 DEF:+45) Basic armor.", 50, 0, 45, 0, 0);
			}
			if (rngArm == 2) {
				ITEM = new Item("Gold armor", "(HP:+75 DEF:+60) Basic armor.", 75, 0, 60, 0, 0);
			}
			if (rngArm == 3) {
				ITEM = new Item("Diamond armor", "(HP:+100 DEF:+100) Basic armor.", 100, 0, 100, 0, 0);
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
			Inventory.money += Enemy.cash_drop;
			System.out.println("You've obtained $" + Enemy.cash_drop + "!");
		}

		// |PRINTING| //
		if (rngDrop <= 5) {// weapon
			System.out.println("You've obtained " + WEP.name + "!");
			System.out.println();
			Inventory.addWep(WEP);
		} else if (rngDrop > 5 && rngDrop <= 10) {// armour
			System.out.println("You've obtained " + ITEM.name + "!");
			System.out.println();
			Inventory.addArmour(ITEM);
		} else if (rngDrop > 10 && rngDrop <= 30) {// accessories
			System.out.println("You've obtained " + ITEM.name + "!");
			System.out.println();
			Inventory.addAccessory(ITEM);
		}
	}
}// Fight
