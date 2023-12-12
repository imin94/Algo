import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node27945 implements Comparable<Node27945>{
	int from;
	int to;
	int w;
	
	public Node27945(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node27945 o) {
		return w-o.w;
	}
	
}

public class Main {
	
	public static int n;
	public static int[] parents;
	public static Queue<Node27945> pq = new PriorityQueue<>();

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
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.add(new Node27945(a,b,c));
		}
		
		bw.write(String.valueOf(kruskal()));
		br.close();
		bw.close();
		
	}
	
	public static int find(int node) {
		if(parents[node]==node)
			return node;
		return parents[node] = find(parents[node]); 
	}
	
	public static int kruskal() {
		int day = 1;
		
		while(!pq.isEmpty()) {
			Node27945 now= pq.poll();
			int from = now.from;
			int to = now.to;
			
			if(day>now.w)
				continue;
			else if(day<now.w)
				return day;
			
			if(find(from)!=find(to)) {
				parents[find(from)] = find(to);
				day++;
			}
		}
		return day;
	}

}