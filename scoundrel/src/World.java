import java.util.Scanner;

public class World {
	public static int bossCount = 0;
	public static int lvlCount = 0;
	static boolean startup = true;
	static Scanner in = new Scanner(System.in);
	static Typewriter typer = new Typewriter();
	
	public static void main(String[] args) {
		startUp();
		nextLvl();
	}// main

	public static void nextLvl() {
		lvlCount += 1;
		if (bossCount == 3) {
			bossCount = 0;
			Enemy.newEnemy("Banshee");
			System.out.println("you have entered the Banshee's dwelling!");
			System.out.println("*** " + Enemy.NAME + " STATS ***");
			System.out.println("HP: " + Enemy.HP);
			System.out.println("Defense: " + Enemy.DEFENSE);
			System.out.println("Attack: " + Enemy.ATTACK);
			System.out.println("Luck: " + Enemy.LUCK);
			System.out.println("Agility: " + Enemy.AGILITY);
			System.out.println();

			Fight.HP = Fight.initialHP + Fight.defense;
			Fight.MP = Fight.initialMP;

			Fight.combat();
		} else {
			bossCount += 1;
			int shopChance = (int) (Math.random() * 100) + 1;// chance of having a shop before a lvl
			if (shopChance <= 40 && !startup) {
				Shopkeep.shop();
			}
			startup = false;

			LevelGenerator.generateLvl();
			Player.findPlayer();
			Control.keyInput();
		}
	}

	/**
	 * This is the basic startup to the game.
	 */
	public static void startUp() {
		String text1 = "The world is bountiful in monsters and magic, and humanity has a thirst for adventure and treasure.";
		String text2 = "And you, alongside many others, have decided to venture into a deep and mysterious dungeon filled with mysterious entities";
		String text2contd = "to embark on a journey with no end.";
		String text3 = "Your adventure begins, ambitious scoundrel!";

		System.out.println(" _____   _____   _____   _   _   _   _  ______  ______   _____   _     \r\n"
				+ "/  ___| /  __ \\ |  _  | | | | | | \\ | | |  _  \\ | ___ \\ |  ___| | |    \r\n"
				+ "\\ `--.  | /  \\/ | | | | | | | | |  \\| | | | | | | |_/ / | |__   | |    \r\n"
				+ " `--. \\ | |     | | | | | | | | | . ` | | | | | |    /  |  __|  | |    \r\n"
				+ "/\\__/ / | \\__/\\ \\ \\_/ / | |_| | | |\\  | | |/ /  | |\\ \\  | |___  | |____\r\n"
				+ "\\____/   \\____/  \\___/   \\___/  \\_| \\_/ |___/   \\_| \\_| \\____/  \\_____/\r\n"
				+ "                                                                       \r\n"
				+ "                                                                       ");
		System.out.println("							[By: Reanjelica Picoc (Smol) & Bryan Lumayno (Tol)]");
		typer.type(text1);
		typer.type(text2);
		typer.type(text2contd);
		typer.type(text3);
		askName();
	}

	public static void askName() {
		System.out.println("");
		System.out.print("What is your name? ");
		String name = in.nextLine();
		String player_name = name;

		System.out.print(player_name + "... Is that your name? (y = yes, n = no) ");

		String reply = in.nextLine();
		reply.toLowerCase();

		if (reply.equals("y")) {
			System.out.println("");
			String welcome = "Greetings, aspiring thief " + player_name + "!";
			String welcome2 = "Your journey is about to begin. But, some key elements to remember: ";

			String control = "CONTROLS:";
			String last_remark = "You certainly look ready, anyways. Welcome to the dungeon!";

			typer.type(welcome);
			typer.type(welcome2);
			typer.type(control);
			System.out.println();

			System.out.println("WASD - Move in four directions");
			System.out.println("E - Access your inventory");
			System.out.println();
			System.out.println("- '@' will be your player character");
			System.out.println("- 'X' is the gate to the next level of the dungeon");
			System.out.println("- 'H' are treasure chests filled with goods to assist your adventure");
			System.out.println();
			System.out.println("- Press enter every time to do an action (like moving around the dungeon)");
			System.out.println("- Be weary of pits around the dungeon");
			System.out.println("- As you go deeper in the dungeon, more variety of creatures will start to appear");
			System.out.println("- There will be a chance of encountering a shop after every level");
			System.out.println();
			System.out.print("Are you ready to begin? (type in any response!) ");
			String begin = in.nextLine();
			String begin_game = begin.toLowerCase();

			if (begin_game == begin.toLowerCase()) {
				typer.type(last_remark);
				delay(1000);
			}
		} else {
			askName();
		}
	}
	
	public static void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
} // World
