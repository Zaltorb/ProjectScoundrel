import java.util.concurrent.ThreadLocalRandom;

public class Weapon {
	int mindmg;
	int maxdmg;
	int hitChance;
	String name;
	String description;

	/**
	 * @param wepName    - Weapon name
	 * @param descript   - Weapon description
	 * @param minDmg
	 * @param maxDmg
	 * @param hitPercent - (in %) Chance of hitting or missing target
	 */
	public Weapon(String wepName, String descript, int minDmg, int maxDmg, int hitPercent) {
		mindmg = minDmg;
		maxdmg = maxDmg;
		hitChance = hitPercent;
		name = wepName;
		description = descript;
	}

	public int DMG() {
		int dmg = ThreadLocalRandom.current().nextInt(mindmg, maxdmg + 1);
		return dmg;
	}

	/**
	 * gUEss ThEy nEvEr mISS, hUh?
	 * 
	 * @return boolean - hit
	 */
	public boolean hitOrMiss() {
		boolean hit = false;
		int chance = (int) (Math.random() * 100) + 1;

		if (chance <= hitChance)// hit
		{
			hit = true;
		} else// miss
		{
			hit = false;
		}
		return hit;
	}// hitOrMiss
}// weapon
