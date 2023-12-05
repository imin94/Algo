import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String ans = "";
		
		while(true) {
			char[] chr = br.readLine().toCharArray();
			if(chr.length==1 & chr[0]=='0')
				break;
			ans="yes";
			for(int i=0; i<=chr.length/2; i++) {
				if(chr[i] != chr[chr.length-1-i])
					ans="no";
			}
			sb.append(ans).append('\n');
		}
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}