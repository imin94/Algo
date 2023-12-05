import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int gcd(int a, int b) {
		if(b == 0)
			return a;
		else
			return gcd(b,a%b);
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		bw.write(String.valueOf(gcd(a,b))+'\n');
		bw.write(String.valueOf(a*b/gcd(a,b)));
		br.close();
		bw.close();
	}
}