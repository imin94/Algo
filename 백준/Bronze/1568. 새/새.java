import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int k = 1;
		int cnt = 0;
		
		while(num>0) {
			if(num<k) k=1;
			num-=k;
			k++;
			cnt++;
		}
		System.out.print(cnt);
	}
}