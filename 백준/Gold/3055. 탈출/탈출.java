import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node3055 implements Comparable<Node3055>{
	int r,c,w;
	
	public Node3055(int r, int c, int w) {
		this.r = r;
		this.c = c;
		this.w = w;
	}

	@Override
	public int compareTo(Node3055 o) {
		return w-o.w;
	}
}

public class Main {
	
	public static char[][] arr;
	public static int R, C;
	public static Queue<int[]> que = new LinkedList<>();
	public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//출발지 저장할 배열
		int[] s = new int[2];
		//배열값 입력
		arr = new char[R][C];
		for(int r = 0; r<R; r++) {
			String str = br.readLine();
			for(int c = 0; c<C; c++) {
				char chr = str.charAt(c);
				arr[r][c] = chr;
				if(chr=='S') {
					s[0]=r;
					s[1]=c;
					//출발지의 정보를 저장하고 바닥으로 변환
					arr[r][c] = '.';
				}
				if(chr=='*') {
					que.add(new int[] {r,c});
				}
			}
		}
		
		bw.write(dfs(s[0],s[1]));
		br.close();
		bw.close();
		
	}
	
	public static String dfs(int sr, int sc) {
		Queue<Node3055> pq = new LinkedList<>();
		
		boolean[][] flag = new boolean[R][C];
		while(!que.isEmpty()) {
			int[] now = que.poll();
			pq.add(new Node3055(now[0],now[1],0));
		}
		pq.add(new Node3055(sr,sc,1));
		
		while(!pq.isEmpty()) {
			Node3055 now = pq.poll();
			int r = now.r;
			int c = now.c;
			int w = now.w;
			if(flag[r][c])
				continue;
			flag[r][c] = true;
			
			//도착했으면 탈출까지 걸린 시간을 계산
			if(arr[r][c]=='D')
				return String.valueOf(w/2);
					
			for(int i = 0; i<4; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];
				
				//범위를 벗어나거나, 물이 .이 아닌 곳을 갔거나, 도치가 . || D가 아닌 곳을 갔으면 continue;
				if(dr<0 || dr>=R || dc<0 || dc>=C || flag[dr][dc] || (w%2==0 && arr[dr][dc]!='.') || (w%2==1 && (arr[dr][dc]=='*' || arr[dr][dc]=='X')))
					continue;
				//물이 지나간 곳은 물로 변환
				if(w%2==0)
					arr[dr][dc]='*';
				pq.add(new Node3055(dr,dc,w+2));
			}
		}
		return "KAKTUS";
	}

}