import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class node1647 implements Comparable<node1647>{
	
	int node;
	int weight;
	
	public node1647(int node, int weight) {
		this.node=node;
		this.weight=weight;
	}
	
	@Override
	public int compareTo(node1647 o) {
		return this.weight-o.weight;
	}
	
}

public class Main {
	
	public static int sum;
	public static int max;
	public static boolean[] flag;
	public static List<node1647>[] graph;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		sum = 0;
		max = 0;
		flag = new boolean[n+1];
		graph = new ArrayList[n+1];
		
		for(int i = 1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new node1647(b,c));
			graph[b].add(new node1647(a,c));
			
		}
		prim();
		bw.write(String.valueOf(sum-max));
		br.close();
		bw.close();
		
	}
	
	public static void prim() {
		Queue<node1647> que = new PriorityQueue<>();
		que.add(new node1647(1,0));
		
		while(!que.isEmpty()) {
			node1647 node = que.poll();
			int n = node.node;
			int w = node.weight;
			
			if(flag[n])
				continue;
			flag[n] = true;
			sum+=w;
			max = Math.max(max, w);
			for(node1647 i : graph[n]) {
				if(flag[i.node])
					continue;
				que.add(i);
			}
		}
	}

}