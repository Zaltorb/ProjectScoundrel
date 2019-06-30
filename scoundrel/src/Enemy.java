import java.util.concurrent.ThreadLocalRandom;

public class Enemy {
	static boolean fighting_slime = false;
	static boolean fighting_skeleton = false;
	static boolean fighting_wraith = false;
	static boolean fighting_banshee = false;

	static Typewriter typer = new Typewriter();

	/**
	 * this method can be called on to refer to the enemy's stats.
	 */
	public static void viewStats() {
		System.out.println("*** " + NAME + " STATS ***");
		System.out.println("HP: " + HP);
		System.out.println("Defense: " + DEFENSE);
		System.out.println("Attack: " + ATTACK);
		System.out.println("Luck: " + LUCK);
		System.out.println("Agility: " + AGILITY);
	}

	// BYNAR'S CORNER //
	static String NAME = null;
	public static int HP = 0;
	public static int initialHP = 0;
	public static int DEFENSE = 0;
	public static int ATTACK = 0;
	public static int LUCK = 0;
	public static int AGILITY = 0;
	static int cash_drop = 0;

	/**
	 * create the stats for the enemy that is going to be fighting the player
	 * 
	 * @param name - name of the mob
	 */
	public static void newEnemy(String name) {
		NAME = name;

		if (name == "Slime") {
			HP = (int) (Math.random() * (51 - 20) + 20); // range is 20-50 HP
			DEFENSE = (int) (Math.random() * (26 - 20) + 20); // range is 20-25 defense
			ATTACK = (int) (Math.random() * (31 - 25) + 25); // range is 25-30 attack
			AGILITY = (int) (Math.random() * (21 - 10) + 10); // range is 50-60 agility
			LUCK = 25;

			cash_drop = (int) (Math.random() * (12 - 10) + 10);
			fighting_slime = true;
		} else if (name == "Skeleton Soldier") {
			HP = (int) (Math.random() * (76 - 68) + 68); // range is 68-75 HP
			DEFENSE = (int) (Math.random() * (51 - 48) + 48); // range is 48-50 defense
			ATTACK = (int) (Math.random() * (51 - 48) + 48); // range is 48-50 attack
			AGILITY = (int) (Math.random() * (21 - 17) + 17); // range is 17-20 agility
			LUCK = 25;

			cash_drop = (int) (Math.random() * (21 - 15) + 15);
			fighting_skeleton = true;
		} else if (name == "Wraith") {
			HP = (int) (Math.random() * (91 - 86) + 86); // range is 86-90 HP
			DEFENSE = (int) (Math.random() * (51 - 48) + 48); // range is 48-50 defense
			ATTACK = (int) (Math.random() * (61 - 58) + 58); // range is 58-60 attack
			AGILITY = (int) (Math.random() * (46 - 50) + 46); // range is 46-50 agility
			LUCK = 45;

			cash_drop = (int) (Math.random() * (51 - 30) + 30);
			fighting_wraith = true;
		} else if (name == "Banshee") {
			HP = (int) (Math.random() * (151 - 148) + 148); // range is 148-150 HP
			DEFENSE = (int) (Math.random() * (30 - 28) + 28); // range is 28-30 defense
			ATTACK = (int) (Math.random() * (51 - 48) + 48); // range is 48-50 attack
			AGILITY = (int) (Math.random() * (11 - 9) + 9); // range is 9-10 agility
			LUCK = 50;

			cash_drop = (int) (Math.random() * (121 - 100) + 100);
			fighting_banshee = true;
		}

		initialHP = HP;
	}// Enemy()

	// ENEMY ACTIONS //

	public static void attack() {
		int critChance = (int) (Math.random() * 100 + 1);
		int dmg = 0;

		if (fighting_banshee == true) { // fighting a Banshee
			int heal_chance = (int) (Math.random() * (7) + 1); // 1/7 chance to heal itself

			if (HP <= 50 && heal_chance == 1) {
				int heal = (int) (Math.random() * (51 - 40) + 40); // can recover around 40-50 HP
				HP += heal;
				typer.type("Banshee uses Heal! The banshee has recovered " + heal + " HP!");
			} else {
				dmg = ThreadLocalRandom.current().nextInt(45, 49 + 1);

				if (critChance <= LUCK) { // crit DMG
					dmg *= 2;
					typer.type("Banshee uses a more powerful Piercing Wail! The player loses " + dmg + " HP!");
				} else { // no crit
					typer.type("Banshee uses Piercing Wail! The player loses " + dmg + " HP!");
				}
			}
		} else { // not fighting a Banshee
			if (fighting_slime == true) {
				dmg = ThreadLocalRandom.current().nextInt(5, 7 + 1);
			} else if (fighting_skeleton == true) {
				dmg = ThreadLocalRandom.current().nextInt(10, 14 + 1);
			} else if (fighting_wraith == true) {
				dmg = ThreadLocalRandom.current().nextInt(45, 49 + 1);
			}

			if (critChance <= LUCK) { // crit DMG
				dmg *= 2;

				if (fighting_slime == true) {
					typer.type("Slime uses a more powerful Tackle! The player loses " + dmg + " HP!");
				} else if (fighting_skeleton == true) {
					typer.type("Skeleton Soldier uses a more powerful Cut! The player loses " + dmg + " HP!");
				} else if (fighting_wraith == true) {
					typer.type("Wraith uses a more powerful Curse! The player loses " + dmg + " HP!");
				}
			} else { // no crit
				if (fighting_slime == true) {
					typer.type("Slime uses Tackle! The player loses " + dmg + " HP!");
				} else if (fighting_skeleton == true) {
					typer.type("Skeleton Soldier uses Cut! The player loses " + dmg + " HP!");
				} else if (fighting_wraith == true) {
					typer.type("Wraith uses Curse! The player loses " + dmg + " HP!");
				}
			}
		}
		System.out.println();

		Fight.HP -= dmg;
	}// attack()

	/**
	 * This method determines an enemy's dodging capacity based on the enemy's
	 * agility.
	 * 
	 * @param enemyAgility
	 */
	public static boolean dodge() {
		boolean dodged = false;

		if (AGILITY < 50) {
			int dodge_chance = (int) (Math.random() * (4) + 1);
			if (dodge_chance == 1) {
				dodged = true;
			}
		} else if (AGILITY >= 50 && AGILITY <= 75) {
			int dodge_chance = (int) (Math.random() * (2) + 1);
			if (dodge_chance == 1) {
				dodged = true;
			}
		} else if (AGILITY > 75) {
			int dodge_chance = (int) (Math.random() * (4) + 1);
			if (dodge_chance == 1 || dodge_chance == 2 || dodge_chance == 3) {
				dodged = true;
			}
		}

		return dodged;
	}// dodge

	/**
	 * The enemy attacks the player if they fail to flee
	 */
	public static void fleeRage() {
		typer.type("Your attempt to flee has failed! The " + NAME + " viciously attacks because of your cowardice!");
		System.out.println();

		int dmg = 0;

		if (fighting_slime == true) {
			dmg = (int) (Math.random() * (14 - 10) + 10);
		} else if (fighting_skeleton == true) {
			dmg = (int) (Math.random() * (24 - 20) + 20);
		} else if (fighting_wraith == true) {
			dmg = (int) (Math.random() * (75 - 71) + 71);
		}

		if (fighting_slime == true) {
			typer.type("Slime uses Angered Tackle! The player loses " + dmg + " HP!");
		} else if (fighting_skeleton == true) {
			typer.type("Skeleton Soldier uses Brutal Slash! The player loses " + dmg + " HP!");
		} else if (fighting_wraith == true) {
			typer.type("Wraith uses Hellish Torment! The player loses " + dmg + " HP!");
		}
		System.out.println();

		Fight.HP -= dmg;
	}// fleeRage()
}// Enemy
