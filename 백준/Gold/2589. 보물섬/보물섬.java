import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static char[][] map;
	public static boolean[][] flag;
	public static int row, col;
	public static int max;
	public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		//배열로 입력값을 받음
		map = new char[row][col];
		for(int r = 0; r<row; r++) {
			String str = br.readLine();
			for(int c = 0; c<col; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		
		max = 0;
		//육지가 나올때마다 해당 좌표에 대해 bfs 동작
		for(int r = 0; r<row; r++) {
			for(int c = 0; c<col; c++) {
				if(map[r][c]=='L') {
					//육지마다 방문처리를 초기화
					flag = new boolean[row][col];
					//해당 육지는 방문처리
					flag[r][c] = true;
					bfs(r,c);
				}
			}
		}
		
		bw.write(String.valueOf(max));
		br.close();
		bw.close();

	}
	
	public static void bfs(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {r,c,0});
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			for(int i = 0; i<4; i++) {
				int dr = now[0] + delta[i][0];
				int dc = now[1] + delta[i][1];
				
				//사방 탐색이 배열의 범위를 벗어나거나, 바다이거나, 이미 방문한 육지인 경우 continue
				if(dr<0 || dr>=row || dc<0 || dc>=col || map[dr][dc]=='W' || flag[dr][dc])
					continue;
				
				flag[dr][dc] = true;
				
				max = Math.max(max,now[2]+1);
				que.add(new int[] {dr,dc,now[2]+1});
			}
		}
	}

}