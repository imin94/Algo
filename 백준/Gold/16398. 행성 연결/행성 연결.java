import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node16398 implements Comparable<Node16398>{
	int from;
	int to;
	int w;
	
	public Node16398(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node16398 o) {
		return this.w-o.w;
	}
}

public class Main {
	
	public static int n;
	public static int[] parents;
	public static Queue<Node16398> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		parents = new int[n];
		
		for(int r = 0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			parents[r] = r;
			for(int c = 0; c<n; c++) {
				int w = Integer.parseInt(st.nextToken());
				//2차원 배열중 좌상단에서 우하단으로 이러지는 대각선 기준 우측 자료만을 사용
				if(r<c)
					pq.add(new Node16398(r,c,w));
			}
		}
		
		bw.write(String.valueOf(kruskal()));
		br.close();
		bw.close();
		
	}
	
	//뿌리 노드 찾기
	public static int find(int node) {
		if(parents[node]==node)
			return node;
		return parents[node] = find(parents[node]);
	}
	
	public static long kruskal() {
		long cost = 0;
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Node16398 now = pq.poll();
			int from = now.from;
			int to = now.to;
			//뿌리가 다르다면 노드 연결
			if(find(from) != find(to)) {
				parents[find(to)] = find(from);
				cost += (long) now.w;
				if(++cnt == n-1)
					return cost;
			}
		}
		
		return 0;
	}

}
