import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node16202 implements Comparable<Node16202>{
	
	int from, to, w;

	public Node16202(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node16202 o) {
		return w-o.w;
	}
	
}

public class Main {
	
	public static int n;
	public static boolean flag=false;
	public static int[] parents;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		//간선의 가중치를 기준으로 오름차순 할 우선순위 큐 구현
		Queue<Node16202> pq = new PriorityQueue<>();
		
		for(int a = 1; a<=m; a++) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Node16202(b,c,a));
		}
		
		//k번의 턴 mst 확인
		for(int i = 0; i<k; i++) {
			if(flag) {
				sb.append("0 ");
				continue;
				}
			//매번 새로운 pq를 넣어줌
			sb.append(kruskal(new PriorityQueue<Node16202>(pq))).append(" ");
			//pq의 가중치가 가장 낮은 노드를 하나씩 제거
			if(!pq.isEmpty())
				pq.poll();
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	//노드의 뿌리 찾기
	public static int find(int node) {
		if(parents[node]==node)
			return node;
		return parents[node] = find(parents[node]);
	}
	
	//mst확인
	public static int kruskal(Queue<Node16202> turn) {
		int cnt = 0;
		int cost = 0;
		//부모 초기화
		for(int i = 1; i<=n; i++) {
			parents[i] = i;
		}
		
		while(!turn.isEmpty()) {
			Node16202 now = turn.poll();
			int from = now.from;
			int to = now.to;
			
			//이미 이어져있는 노드라면 continue;
			if(find(from)==find(to))
				continue;
			parents[find(from)] = find(to);
			cost+=now.w;
			
			if(++cnt==n-1)
				return cost;
			
		}
		flag=true;
		return 0;
	}

}