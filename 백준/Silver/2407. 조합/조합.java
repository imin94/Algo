import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BigInteger n = new BigInteger(st.nextToken());
		BigInteger r = new BigInteger(st.nextToken());
		
		bw.write(String.valueOf(combi(n,r)));
		br.close();
		bw.close();

	}
	
	public static BigInteger combi(BigInteger n, BigInteger r) {
		return fact(n).divide( (fact(n.subtract(r)).multiply(fact(r))) );
	}
	
	public static BigInteger fact(BigInteger n) {
		BigInteger num = new BigInteger("1");
		BigInteger one = new BigInteger("1");
		for(BigInteger i = new BigInteger("1"); i.compareTo(n.add(one))==-1; i=i.add(one)) {
			num=num.multiply(i);
		}
		return num;
	}
}