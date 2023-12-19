import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int n, sr, sc, sw, sCnt;
	public static int[][] arr;
	//상 좌 우 하 순서로 탐색
	public static int[][] delta = {{-1,0},{0,-1},{0,1},{1,0}};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		sw = 2;
		sCnt = 0;
		arr = new int[n][n];
		
		int now = 0;
		for(int r = 0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c<n; c++) {
				now = Integer.parseInt(st.nextToken());
				arr[r][c] = now;
				if(now == 9) {
					sr = r;
					sc = c;
					arr[r][c] = 0;
				}
			}
		}
		int cnt = 0;
		while(true){
			if(bfs() == 0)
				break;
			if(++cnt == sw) {
				sw++;
				cnt = 0;
			}
		}
		bw.write(String.valueOf(sCnt));
		br.close();
		bw.close();
		
	}
	
	public static int bfs() {
		
		Queue<int[]> pq = new PriorityQueue<>(
				(o1,o2) -> o1[2]!=o2[2]?Integer.compare(o1[2], o2[2]):
					(o1[0]!=o2[0]?Integer.compare(o1[0],o2[0]):Integer.compare(o1[1], o2[1])));
		pq.add(new int[] {sr,sc,0});
		int[][] flag = new int[n][n]; 
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int r = now[0];
			int c = now[1];
			cnt = now[2];
			
			if(arr[r][c] != 0 && arr[r][c]<sw) {
				arr[r][c] = 0;
				sr=r;
				sc=c;
				sCnt +=cnt;
				return -1;
			}
			for(int i = 0; i<4; i++) {
				int dr = r+delta[i][0];
				int dc = c+delta[i][1];
				
				//배열의 범위를 벗어나는 탐색, 이미 방문, 아기상어보다 물고기가 큰 경우 continue;
				if(dr<0 || dr>=n || dc<0 || dc>=n || flag[dr][dc]!=0 || arr[dr][dc]>sw)
					continue;
				flag[dr][dc] = 1;
				pq.add(new int[] {dr,dc,cnt+1});

			}
		}
		return 0;
	}

}