import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int[][] place = new int[101][101];
		int sum = 0;
		int t = Integer.parseInt(br.readLine());
		int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
		
		for(int tc = 1; tc<=t; tc++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			
			for(int r = row; r<row+10; r++) {
				for(int c = col; c<col+10; c++) {
					if(r==row || r==row+9 || c==col || c==col+9)
						place[r][c]=1;
					else
						place[r][c]+=2;
				}
			}
		}
		
		for(int r = 0; r<101; r++) {
			for(int c = 0; c<101; c++) {
				if(place[r][c]!=1)
					continue;
				int zeroCnt=0;
				if(place[r][c]==1) {
					for(int i = 0; i<4; i++) {
						int dr = r+delta[i][0];
						int dc = c+delta[i][1];
						if(place[dr][dc]==0)
							zeroCnt++;
					}
				}
				sum+=zeroCnt;
			}
		}
		
		bw.write(String.valueOf(sum));
		br.close();
		bw.close();
		
	}

}