import java.io.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int c = Integer.parseInt(br.readLine());
		char[] chr = br.readLine().toCharArray();
		int len = chr.length;
		int r = len/c;
		
		char[][] chrArr = new char[r][c];
		for(int i = 0; i<len; i++) {
			if((i/c)%2==1)
				chrArr[i/c][c-1-i%c]=chr[i];
			else
				chrArr[i/c][i%c]=chr[i];
		}
		
		for(int j = 0; j<c; j++) {
			for(int i = 0; i<r; i++) {
				sb.append(chrArr[i][j]);
			}
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}