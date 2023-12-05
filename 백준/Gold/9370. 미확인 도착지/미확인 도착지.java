import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static List<int[]>[] list;
	public static List<Integer> ans;
	public static int max = Integer.MAX_VALUE;
	public static int n;
	public static int node1;
	public static int node2;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc<testCase; tc++) {

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[n+1];
			ans = new ArrayList<>();
			
			for(int i = 1; i<=n; i++) {
				list[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			node1 = Integer.parseInt(st.nextToken());
			node2 = Integer.parseInt(st.nextToken());
			//가중치를 전부 짝수로 만드렁줌
			for(int i = 0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				list[a].add(new int[] {b,c*2});
				list[b].add(new int[] {a,c*2});
			}
			
			for(int i = 0; i<t; i++) {
				int e = Integer.parseInt(br.readLine());
				
				if(dijk(s,e)%2 == 1)
					ans.add(e);
			}
			Collections.sort(ans);
			for(int i : ans) {
				sb.append(i).append(" ");
			}
			sb.append('\n');
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	public static int dijk(int s, int e) {
		
		int[] cost = new int[n+1];
		for(int i = 1; i<=n; i++) {
			cost[i] = max;
		}
		boolean[] flag = new boolean[n+1];
		
		Queue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1]-b[1]));
		cost[s] = 0;
		pq.add(new int[] {s,0});
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int nowTo = now[0];
			
			if(flag[nowTo])
				continue;
			flag[nowTo] = true;
			
			if(nowTo == e) {
				return now[1];
			}
			for(int[] next : list[nowTo]) {
				int nextTo = next[0];
				int nextW = next[1];
				//최단 경로가 여러개일 수 있으니 >=으로 비교
				if(!flag[nextTo] && cost[nextTo]>=cost[nowTo]+nextW) {
					//포함되는 경우 -1을 해주어 홀추로 출력될 수 있게 해줌
					if((node1 == nowTo && node2 == nextTo) || (node1==nextTo && node2==nowTo))
						cost[nextTo] = cost[nowTo]+nextW-1;
					else
						cost[nextTo] = cost[nowTo]+nextW;
						
					pq.add(new int[] {nextTo,cost[nextTo]});
				}
			}
		}
		return 0;
	}

}
