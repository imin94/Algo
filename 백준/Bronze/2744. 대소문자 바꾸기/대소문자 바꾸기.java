import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String answer = "";
		for(int i=0; i<str.length(); i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				answer+=str.substring(i,i+1).toLowerCase();
			}
			else {
				answer+=str.substring(i,i+1).toUpperCase();
			}
		}
		System.out.println(answer);
	}
}