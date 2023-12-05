import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char[] chr = br.readLine().toCharArray();
		int ans = 1;
		
		for(int i=0; i<=chr.length/2; i++) {
			if(chr[i] != chr[chr.length-1-i])
				ans=0;
		}
		bw.write(""+ans);
		br.close();
		bw.close();
	}
}