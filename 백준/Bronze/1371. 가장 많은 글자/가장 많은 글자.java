import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] alphaArray = new int[26];
		
		while (sc.hasNextLine()) {
			String text = sc.nextLine();
			for (int i=0; i<text.length(); i++) {
				if(text.charAt(i)>='a' && text.charAt(i)<='z') {
					alphaArray[text.charAt(i)-'a']++;
				}
			}
		}
		int max=0;
		for (int i=0; i<26; i++) {
			if (max < alphaArray[i]) {
				max = alphaArray[i];
			}
		}
		for (int i=0; i<26; i++) {
			if(max==alphaArray[i]) {
				System.out.print((char) (i+'a'));
			}
		}
	}
}