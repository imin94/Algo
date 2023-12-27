import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int[][] cheeze;
	public static int R, C;
	public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cheeze = new int[R][C];
		
		for(int r = 0; r<R; r++) {
			st= new StringTokenizer(br.readLine());
			for(int c = 0; c<C; c++) {
				cheeze[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		while(true) {
			if(bfs()==1)
				break;
			cnt++;
			for(int r = 0; r<R; r++) {
				for(int c = 0; c<C; c++) {
				}
			}
		}
		
		bw.write(String.valueOf(cnt));
		br.close();
		bw.close();
		
	}
	
	public static int bfs() {
		int[][] arr = new int[R][C];
		boolean[][] flag = new boolean[R][C];
		
		Queue<int[]> que = new LinkedList<>();
		Queue<int[]> CQue = new LinkedList<>();
		
		que.add(new int[] {0,0});
		
		//0,0에서 시작해 치즈랑 닿아있는 외부 공기를 arr배열에 2로 표시함
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int r = now[0];
			int c = now[1];
			
			if(flag[r][c])
				continue;
			flag[r][c] = true;
			
			for(int i = 0; i<4; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];
				
				if(dr<0 || dr>=R || dc<0 || dc>=C || flag[dr][dc] || arr[dr][dc]==2)
					continue;
				//외부랑 닿아있는 치즈를 CQue에 넣음
				if(cheeze[dr][dc]==1) {
					CQue.add(new int[] {dr,dc});
					arr[r][c] = 2;
					continue;
				}
				arr[dr][dc] = 2;
				
				que.add(new int[] {dr,dc});
			}
		}
		
		//치즈가 확인되지 않으면 멈춤
		if(CQue.isEmpty())
			return 1;
		
		//치즈에대해 4방 탐색해 외부 공기가 2번이상 탐색되면 해당 녹일 치즈로 구분해 mel que에 넣음
		Queue<int[]> mel = new LinkedList<>();
		while(!CQue.isEmpty()) {
			int[] now = CQue.poll();
			int r = now[0];
			int c = now[1];
			
			int cnt = 0;
			for(int i = 0; i<4; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];
				
				if(dr<0 || dr>=R || dc<0 || dc>=C || cheeze[dr][dc]==1 || arr[dr][dc] !=2)
					continue;
				cnt++;
			}
			if(cnt>1)
				mel.add(new int[] {r,c});
		}
		
		//녹은 치즈 갱신
		while(!mel.isEmpty()) {
			int[] now = mel.poll();
			int r = now[0];
			int c = now[1];
			
			cheeze[r][c] = 0;
		}
		
		return 0;
	}

}