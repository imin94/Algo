import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int[][] score = new int[2][3];
	public static int cnt;
	public static int N;
	public static int M;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int r = 0; r < 2; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < 3; c++) {
				score[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		spy(0,0,-1);
		
		bw.write(String.valueOf(cnt));
		br.close();
		bw.close();
	}
	
	public static void spy(int depth, int sum, int yesterday) {
		if(depth==N) {
			if(sum>=M)
				cnt++;
			return;
		}
		for(int r = 0; r<2; r++) {
			for(int c = 0; c<3; c++) {
				if(yesterday==c) {
					spy(depth+1,sum + score[r][c]/2,c);
				}
				else {
					spy(depth+1,sum + score[r][c],c);
				}
			}
		}
	}

}