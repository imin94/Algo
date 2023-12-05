import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	public static int n;
	public static char[][] arr1;
	public static char[][] arr2;
	public static boolean[][] flag;
	public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		arr1 = new char[n][n];
		arr2 = new char[n][n];
		flag = new boolean[n][n];
		
		//arr1: 기본 색상 배열
		//arr2: 색약 전용 색상 배열
		for(int r = 0; r<n; r++) {
			String str = br.readLine();
			for(int c = 0; c<n; c++) {
				char chr = str.charAt(c);
				arr1[r][c] = chr;
				if(chr == 'R')
					arr2[r][c] = 'G';
				else
					arr2[r][c] = chr;
			}
		}
		
		//기본 색상 배열의 bfs 동작
		int cnt = 0;
		for(int r = 0; r<n; r++) {
			for(int c = 0; c<n; c++) {
				if(flag[r][c])
					continue;
				cnt++;
				bfs(arr1,r,c);
			}
		}
		
		bw.write(cnt+" ");
		
		//방문처리와 cnt를 재할당 및 초기화한 후 색약 전용 배열의 bfs 동작
		cnt = 0;
		flag = new boolean[n][n];
		for(int r = 0; r<n; r++) {
			for(int c = 0; c<n; c++) {
				if(flag[r][c])
					continue;
				cnt++;
				bfs(arr2,r,c);
			}
		}
		
		bw.write(String.valueOf(cnt));
		br.close();
		bw.close();
		
	}
	
	public static void bfs(char[][] array, int row, int col) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {row,col});
		flag[row][col] = true;
		
		char color =  array[row][col];
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int r = now[0];
			int c = now[1];
			
			for(int i = 0; i<4; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];
				
				if(dr<0 || dr>=n || dc<0 || dc>=n || flag[dr][dc] || color!=array[dr][dc])
					continue;
				flag[dr][dc] = true;
				que.add(new int[] {dr,dc});
			}
		}
	}

}