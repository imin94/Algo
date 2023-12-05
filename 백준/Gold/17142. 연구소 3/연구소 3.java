import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int M;
	public static int placeCnt=0;
	public static int min = Integer.MAX_VALUE;
	public static int[][] lab;
	public static List<int[]> virus = new ArrayList<>();
	public static int[][] virusFlag;
	public static boolean[][] visit;
	public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][N];
		
		for(int r = 0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c<N; c++) {
				int num = Integer.parseInt(st.nextToken());
				lab[r][c] = num;
				if(num == 2) {
					virus.add(new int[] {r,c,0});
				}
				else if(num == 0)
					placeCnt++;
			}
		}
		
		virusFlag = new int[M][1];
		if(placeCnt==0)
			min = 0;
		else
			dfs(0,0);
		if(min == Integer.MAX_VALUE)
			min = -1;
		bw.write(String.valueOf(min));
		br.close();
		bw.close();
	}
	
	public static void dfs(int depth, int idx) {
		if(depth==M) {
			visit = new boolean[N][N];
			bfs();
			return;
		}
		
		for(int i = idx; i<virus.size(); i++) {
			virusFlag[depth] = virus.get(i);
			dfs(depth+1, i+1);
		}
	}
	
	public static void bfs() {
		int cnt = placeCnt;
		Queue<int[]> que = new LinkedList<>();
		for(int i = 0; i<M; i++) {
			que.add(virusFlag[i]);
			visit[virusFlag[i][0]][virusFlag[i][1]] = true;
		}
		
		while(!que.isEmpty()) {
			int[] rc = que.poll();
			int r = rc[0];
			int c = rc[1];
			int w = rc[2];

			for(int i = 0; i<4; i++) {
				int dr = r+delta[i][0];
				int dc = c+delta[i][1];
				if(dr<0 || dr>=N || dc<0 || dc>=N || visit[dr][dc] || lab[dr][dc]==1)
					continue;
				visit[dr][dc] = true;
				if(lab[dr][dc]==0)
					cnt--;
				if(cnt==0) {
					min = Math.min(min, w+1);
					return;
				}
				que.add(new int[] {dr,dc,w+1});
			}
		}
	}

}