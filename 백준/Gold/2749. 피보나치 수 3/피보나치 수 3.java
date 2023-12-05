import java.io.*;
import java.math.BigInteger;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int joogi = 1000000/10*15; //파시노주기
		long n = Long.parseLong(br.readLine())%joogi;		
		
		if(n<=1)
			bw.write(String.valueOf(n));
		else
			bw.write(String.valueOf(pibo(n)));
		bw.close();
		br.close();
	}
	
	public static long pibo(long n) {
		long temp1;
		long temp2;
		Stack<Long> stack = new Stack<Long>();
		stack.push((long) 0);
		stack.push((long) 1);

		for(int i = 1 ; i<n ; i++) {
			temp1=stack.pop();
			temp2=stack.pop();
			temp2=(temp1+temp2)%1000000;
			stack.push(temp1);
			stack.push(temp2);
		}
		
		return stack.pop();
		
	}

}