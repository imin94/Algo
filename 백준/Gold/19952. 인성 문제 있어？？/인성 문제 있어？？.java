import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int r;
	public static int c;
	public static int p;
	public static int sr;
	public static int sc;
	public static int er;
	public static int ec;
	public static int[][] maze;
	public static boolean[][] flag;
	public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=t; tc++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			er = Integer.parseInt(st.nextToken());
			ec = Integer.parseInt(st.nextToken());
			
			maze = new int[r][c];
			flag = new boolean[r][c];
			
			for(int i = 0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int tempr = Integer.parseInt(st.nextToken());
				int tempc = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				maze[tempr-1][tempc-1] = h;
			}
			
			if(dijkstra())
				System.out.println("잘했어!!");
			else
				System.out.println("인성 문제있어??");
			
		}
		
	}
	
	public static boolean dijkstra() {
		
		Queue<node19952> pQue = new LinkedList<>();
		pQue.add(new node19952 (sr-1,sc-1,p));
		flag[sr-1][sc-1]=true;
		
		while(!pQue.isEmpty()) {
			node19952 temp = pQue.poll();
			int tempr = temp.r;
			int tempc = temp.c;
			
			for(int i = 0; i<4; i++) {
				int dr = tempr + delta[i][0];
				int dc = tempc + delta[i][1];

				if(dr<0 || dr>=r || dc<0 || dc>=c || flag[dr][dc] || temp.p<1)
					continue;
				
				if(maze[dr][dc]-maze[tempr][tempc]>temp.p)
					continue;
				
				if(dr==er-1 && dc==ec-1) {
					return true;
				}
				flag[dr][dc]=true;
				pQue.add(new node19952 (dr,dc,temp.p-1));
			}
		}
		return false;
		
	}
	
	static class node19952{
		int r;
		int c;
		int p;
		
		public node19952(int r, int c, int p) {
			this.r = r;
			this.c = c;
			this.p = p;
		}
		
	}

}