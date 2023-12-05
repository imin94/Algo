import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] iArr = new int[10];
		String[] strArr = br.readLine().split("");
		
		for(int i = 0; i<strArr.length; i++) {
			iArr[Integer.parseInt(strArr[i])]++;
		}
		int yukgu = ((iArr[6]+iArr[9])+1)/2;
		iArr[6]=yukgu;
		iArr[9]=yukgu;
		
		int max=0;
		for(int i = 0; i<10; i++) {
			if(iArr[i]>max) max=iArr[i];
		}
		System.out.println(max);
		
	}

}