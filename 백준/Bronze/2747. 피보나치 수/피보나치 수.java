import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] pibo = new int[n+1];
		for(int i = 0; i<=n; i++) {
			if(i<2)
				pibo[i]=i;
			else
				pibo[i]=pibo[i-1]+pibo[i-2];
		}
		bw.write(String.valueOf(pibo[n]));
		br.close();
		bw.close();
	}
}