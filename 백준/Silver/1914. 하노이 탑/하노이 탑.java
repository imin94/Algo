import java.io.*;
import java.math.BigInteger;

public class Main {
	
	public static StringBuilder sb = new StringBuilder();
	public static int cnt = 0;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		if(n<=20) {
			tops(n,1,2,3);
			bw.write(""+cnt+'\n');
			bw.write(sb.toString());
		}
		if(n>20) {
			BigInteger big = new BigInteger("2");
			BigInteger e = new BigInteger("2");
			BigInteger il = new BigInteger("1");
			for(int i = 1; i<n; i++) {
				big=big.multiply(e);
			}
			big=big.subtract(il);
			bw.write(big.toString());
		}
		
		br.close();
		bw.close();
		
	}
	
	public static void tops(int n, int A, int B, int C) {
		cnt++;
		if(n==1) {
			top(1,A,C);
		}
		else {
			tops(n-1,A,C,B);
			top(n,A,C);
			tops(n-1,B,A,C);
		}
	}
	
	public static void top(int n, int start, int end) {
		sb.append(start+" "+end+'\n');
	}


}