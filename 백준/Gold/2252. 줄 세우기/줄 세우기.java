import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static int m;
	public static int[] degree;
	public static List<Integer>[] graph;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n+1];
		degree = new int[n+1];
		for(int i = 1; i<=n; i++){
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			degree[b]++;
		}
		
		topological();
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	public static void topological() {
		Queue<Integer> que = new LinkedList<>();
		for(int i = 1; i<=n; i++) {
			if(degree[i]==0)
				que.add(i);
		}
		
		while(!que.isEmpty()){
			int num = que.poll();
			if(degree[num]==0)
				sb.append(num).append(" ");
			for(int i : graph[num]) {
				if(--degree[i]==0)
					que.add(i);
			}
		}
	}

}