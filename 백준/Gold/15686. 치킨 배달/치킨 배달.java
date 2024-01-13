import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	
	public static int n, m, min, size;
	public static int[][] cost;
	public static boolean[] flag;
	public static List<int[]> chicken = new ArrayList<>();
	public static List<int[]> home = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		min = 987654321;
		cost = new int[n][n];
		
		for(int r = 0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c<n; c++) {
				cost[r][c] = 987654321;
				int value = Integer.parseInt(st.nextToken());
				if(value == 2)
					chicken.add(new int[] {r,c});
				if(value == 1)
					home.add(new int[] {r,c});
			}
		}
		
		size = chicken.size();
		flag = new boolean[size];
		
		dfs(0,0);
		
		bw.write(String.valueOf(min));
		br.close();
		bw.close();
		
	}
	
	public static void dfs(int cnt, int depth) {
		//치킨집은 m보다 크거나 같다
		if(cnt == m || depth==size) {
			
			if(cnt == 0)
				return;
			
			for(int r = 0; r<n; r++) {
				for(int c = 0; c<n; c++) {
					cost[r][c] = 987654321;
				}
			}
			
			for(int i = 0; i<size; i++) {
				if(!flag[i])
					continue;
				int[] chic = chicken.get(i);
				int r = chic[0];
				int c = chic[1];
				for(int j = 0; j<home.size(); j++) {
					int rh = home.get(j)[0];
					int ch = home.get(j)[1];
					int value = Math.abs(r-rh) + Math.abs(c-ch);
					if(cost[rh][ch]>value)
						cost[rh][ch] = value;
				}
			}
			int sum = 0;
			for(int i = 0; i<home.size(); i++) {
				sum += cost[home.get(i)[0]][home.get(i)[1]];
			}
			
			min = Math.min(min, sum);
			
			return;
		}
		dfs(cnt,depth+1);
		flag[depth] = true;
		dfs(cnt+1,depth+1);
		flag[depth] = false;
		
	}

}