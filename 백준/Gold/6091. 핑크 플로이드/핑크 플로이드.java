import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node6091 implements Comparable<Node6091>{
	
	int from,to,w;

	public Node6091(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node6091 o) {
		return w-o.w;
	}
	
}

public class Main {
	
	public static int n;
	public static int[] parents;
	public static Queue<Node6091> pq = new PriorityQueue<>();
	public static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		parents = new int[n+1];
		list = new ArrayList[n+1];
		for(int i = 1; i<=n; i++) {
			parents[i] = i;
			list[i] = new ArrayList<>();
		}
		
		//모든 간선의 정보 우선순위 큐에 입력
		int w = 0;
		for(int r = 1; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = r+1; c<=n; c++) {
				w = Integer.parseInt(st.nextToken());
				pq.add(new Node6091(r,c,w));
			}
		}
		
		//kruskal로 mst의 간선의 정보를 인접리스트에 입력
		kruskal();
		
		//list 정렬
		for(int i = 1; i<=n; i++) {
			Collections.sort(list[i]);
		}
		
		//list에 저장된 mst의 정보 출력
		for(int i = 1; i<=n; i++) {
			sb.append(list[i].size()).append(" ");
			for(int j = 0; j<list[i].size(); j++) {
				sb.append(list[i].get(j)).append(" ");
			}
			sb.append('\n');
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	//root 찾기
	public static int find(int node) {
		if(parents[node]==node)
			return node;
		return parents[node] = find(parents[node]);
	}
	
	public static void kruskal() {
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Node6091 now = pq.poll();
			
			int from = now.from;
			int to = now.to;
			int w= now.w;
			
			int rootF = find(from);
			int rootT = find(to);
			if(rootF == rootT)
				continue;
			parents[rootF] = rootT;
			
			list[from].add(to);
			list[to].add(from);
			
			if(++cnt==n-1)
				return;
		}
	}

}