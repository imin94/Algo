import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static int[] maxArr;
	public static int[] build;
	public static int[] degree;
	public static List<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=t ;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			maxArr = new int[n+1];
			degree = new int[n+1];
			graph = new ArrayList[n+1];
			
			for(int i = 1; i<=n; i++) {
				graph[i] = new ArrayList<>();
			}
			
			build = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=n; i++) {
				build[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				degree[b]++;
			}
			
			int last = Integer.parseInt(br.readLine());
			topological(last);
			sb.append(Math.max(maxArr[last],build[last])).append('\n');
			
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	public static void topological(int last) {
		
		Queue<int[]> que = new LinkedList<>();
		
		for(int i = 1; i<=n; i++) {
			if(degree[i]==0) {
				que.add(new int[] {i,build[i]});
			}
		}
		
		while(!que.isEmpty()) {
			int[] edge = que.poll();
			if(edge[0]==last)
				break;
			for(int i : graph[edge[0]]) {
				maxArr[i] = Math.max(maxArr[i], edge[1]+build[i]);
				if(--degree[i]==0)
					que.add(new int[] {i,maxArr[i]});
			}
		}
	}

}