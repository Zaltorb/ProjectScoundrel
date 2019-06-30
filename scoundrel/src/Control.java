import java.util.Scanner;

public class Control {
	public static boolean actionloop = true;
	static Typewriter typer = new Typewriter();
	static Scanner in = new Scanner(System.in);

	/**
	 * All player input while traversing map will be here
	 */
	public static void keyInput() {

		while (actionloop) {
			System.out.println("Dungeon lvl: " + World.lvlCount + " | Gold: $" + Inventory.money + " | H - help");
			System.out.print("Action? ");
			String INPUT = in.nextLine();
			INPUT = INPUT.toLowerCase();

			if (INPUT.equals("w") || INPUT.equals("a") || INPUT.equals("s") || INPUT.equals("d")) {
				Player.movement(INPUT);
			} else if (INPUT.equals("e")) {
				Inventory.equipment();
			} else if (INPUT.equals("h")) {
				help();
			} else if (INPUT.equals("carby")) {
				typer.type("With the snap of your finger, you go to the next dungeon!");
				World.delay(1000);
				World.nextLvl();
			}
		} // while
	}// keyInput

	public static void help() {
		System.out.println("------------------------------");
		System.out.println("WASD - Move in four directions");
		System.out.println("E - Access your inventory");
		System.out.println();
		System.out.println("- '@' will be your player character");
		System.out.println("- 'X' is the gate to the next level of the dungeon");
		System.out.println("- 'H' are treasure chests filled with goods to assist your adventure");
		System.out.println();
		System.out.println("Press enter every time to do an action");
		System.out.println("Use cheatcode 'Carby' while exploring, during battle, and in the shop");
		System.out.println("------------------------------");
	}//help()
	
	/**
	 * The beginning titles and blurb when game is started up.
	 */
	public static void startUp() {

	}
}// Control
