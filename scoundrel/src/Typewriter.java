
public class Typewriter

{
	public void type(String text) {
		for (int t = 0; t < text.length(); t++) {
			System.out.printf("%c", text.charAt(t));
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}

	public void fastType(String text) {
		for (int t = 0; t < text.length(); t++) {
			System.out.printf("%c", text.charAt(t));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}

	public void slowType(String text) {
		for (int s = 0; s < text.length(); s++) {
			System.out.printf("%c", text.charAt(s));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}
}
