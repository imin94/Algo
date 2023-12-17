import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node2406 implements Comparable<Node2406>{
	int from, to, w;

	public Node2406(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node2406 o) {
		return w-o.w;
	}
	
}

public class Main {
	
	public static int n;
	public static int[] parents;
	public static StringBuilder sb = new StringBuilder();
	public static Queue<Node2406> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1];
		for(int i = 1; i<=n; i++) {
			parents[i] = i;
		}
		
		//기존에 연결된 간선
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			
			int rootA = find(to);
			int rootB = find(from);
			if(rootA != rootB)
				parents[rootA] = rootB;
		}
		
		for(int r = 1; r<=n; r++) {
			st = new StringTokenizer(br.readLine());
			if(r==1)
				continue;
			for(int c = 1; c<=n; c++) {
				int w = Integer.parseInt(st.nextToken());
				if(r>=c)
					continue;
				pq.add(new Node2406(r,c,w));
			}
		}
				
		bw.write(kruskal());
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	public static int find(int node) {
		if(parents[node]==node)
			return node;
		return parents[node] = find(parents[node]);
	}
	
	public static String kruskal() {
		int cnt = 0;
		int cost = 0;
		
		while(!pq.isEmpty()) {
			Node2406 now = pq.poll();
			int from = now.from;
			int to = now.to;
			
			//연결된 그래프일 경우 continue;
			if(find(from)==find(to))
				continue;
			
			parents[find(from)] = find(to);
			cnt++;
			cost+=now.w;
			sb.append(from+" "+to).append('\n');
		}
		
		return cost+" "+cnt+"\n";
	}

}