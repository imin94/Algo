import java.io.*;
import java.lang.Math;

public class Main {
	
	static int[] curCol;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		curCol = new int[N];
		dfs(N,0);
		bw.write(String.valueOf(cnt));
		br.close();
		bw.close();
	}
	
	public static void dfs(int n, int depth) {
		if(depth == n) {
			cnt++;
			return;
		}
		for(int i = 0; i<n; i++) {
			curCol[depth]=i;
			if(attack(depth))
				dfs(n,depth+1);
		}
	}
	
	public static boolean attack(int findRow) {
		for(int row = 0; row<findRow; row++) {
			//기존에 위치한 위치한 퀸들의 열위치 curCol[r]과 현재 탐색하는 열이 동일한 경우(같은 행과 열에는 둘 수 없다) false
			if(curCol[row]==curCol[findRow])
				return false;
			//기존에 위치한 퀸들의 행열 위치 row,curCol[row]과 현재 탐색하는 행열의 차가 동일한 경우(대각에 위치한 경우) false
			if(Math.abs(row-findRow) == Math.abs(curCol[row]-curCol[findRow]))
				return false;
		}
		
		return true;
	}

}
