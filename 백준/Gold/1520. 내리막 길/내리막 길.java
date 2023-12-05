import java.io.*;
import java.util.StringTokenizer;

public class Main {

	
	public static int n;
	public static int m;
	public static int[][] map;
	public static int[][] route;
	public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		route = new int[n][m];
		
		for(int r = 0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				route[r][c]=-1;
			}
		}
		System.out.println(dfs(0,0));
		
	}
	
	public static int dfs(int r, int c) {
		if(r==n-1 && c==m-1)
			return 1;
		if(route[r][c] != -1) {
			return route[r][c];
		}else {
			route[r][c] =0;
			for(int i = 0; i<4; i++) {
				int dr = r+delta[i][0];
				int dc = c+delta[i][1];
				if(dr<0 || dr>=n || dc<0 || dc>=m || map[dr][dc]>=map[r][c])
					continue;
				route[r][c]+=dfs(dr,dc);
			}
		}
		
		return route[r][c];
	}

}