import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static int m;
	public static int max = Integer.MIN_VALUE;
	public static int[][] lab;
	public static boolean[][] flagDfs;
	public static boolean[][] flagBfs;
	public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		lab = new int[n][m];
		flagDfs = new boolean[n][m];
		for(int r = 0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c<m; c++) {
				lab[r][c]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0,0);
		bw.write(String.valueOf(max));
		br.close();
		bw.close();
		
		
	}
	
	public static void dfs(int r, int c, int depth) {
		
		//깊이가 3이면(벽을 세 개 세움) bfs 탐색을해 바이러스가 얼마나 퍼지는지 확인
		if(depth==3) {
			flagBfs = new boolean[n][m];
			bfs();
			return;
		}
		//r행의 모든 열을 탐색 했으면 r+1행을 탐색 시작
		if(c==m) {
			dfs(r+1,0,depth);
			return;
		}
		//n-1행 까지 탐색을 완료했다면 탐색 종료
		if(r==n) {
			return;
		}
		//0,0 부터 n-1,m-1까지 좌표 탐색
		for(int row = 0; row<n; row++) {
			for(int col = 0; col<m; col++) {
				//이미 방문한 좌표라면 continue;
				if(flagDfs[row][col])
					continue;
				//방문한 좌표가 아니라면 방문 표시
				flagDfs[row][col]=true;
				//벽이거나 바이러스인 경우 depth를 증가시키지 않고 다음 좌표로 dfs 재귀
				if(lab[row][col] != 0) {
					dfs(row,col+1,depth);
				}
				//좌표가 0인 경우(빈 칸인 경우)
				else {
					//해당 좌표에 벽을 건설하고 depth를 증가시킨 다음 좌표로 dfs 재귀
					lab[row][col]=1;
					dfs(row,col+1,depth+1);
					//헤당 좌표에 대해 모든 재귀가 종료된 경우 다시 0(빈칸)으로 바꾸고 방문도 해제
					lab[row][col]=0;
					flagDfs[row][col]=false;
				}
				
			}
		}
		
	}
	
	public static void bfs() {
		
		Queue<int[]> que = new LinkedList<int[]>();
		//바이러스가 퍼지거나 벽인 경우 증가시킬 cnt 초기화
		int cnt = 0;
		//연구실의 모든 좌표를 순회하면서, 바이러스가 존재하는 곳을 que에 넣고
		//바이러스와 벽의 개수를 카운트
		for(int r = 0; r<n; r++) {
			for(int c = 0; c<m; c++) {
				if(lab[r][c]==1)
					cnt++;
				else if(lab[r][c]==2) {
					que.add(new int[] {r,c});
					cnt++;
				}
			}
		}
		
		//que가 비는 경우는 바이러스가 더 이상 퍼질 수 없는 경우
		while(!que.isEmpty()) {
			//바이러스에 감염된 공간의 좌표를 출력
			int[] rc = que.poll();
			for(int i = 0; i<4; i++) {
				int dr = rc[0] + delta[i][0];
				int dc = rc[1] + delta[i][1];
				
				//해당 좌표를 기점으로 사방탐색
				if(dr<0 || dr>=n || dc<0 || dc>=m || lab[dr][dc]!=0 || flagBfs[dr][dc])
					continue;
				//방문하지 않았으며, 바이러스가 퍼질 수 있는 좌표인 경우
				//que에 해당 좌료플 넣고 방문여부를 true로 바꾸고 카운트를 증가
				que.add(new int[] {dr,dc});
				flagBfs[dr][dc]=true;
				cnt++;
			}
		}
		//cnt에는 벽이거나 바이러스인 공간이 들어있음
		int safezone = n*m-cnt;
		max = Math.max(max, safezone);
		
	}

}