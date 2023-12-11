import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node23034 implements Comparable<Node23034>{
	
	int from;
	int to;
	int w;
	
	public Node23034(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node23034 o) {
		return this.w - o.w;
	}
	
}

public class Main {
	
	public static int n;
	public static int[] parents;
	public static List<Node23034>[] list;
	public static Queue<Node23034> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1];
		//최소 간선 트리를 담아놓을 list
		list = new ArrayList[n+1];
		
		for(int i = 1; i<=n; i++) {
			parents[i] = i;
			list[i] = new ArrayList<>();
		}
		
		//노드들이 연결된 간선을 가중치로 오름차순하여 pq에 넣음
		for(int i = 1; i<=m; i++) {
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.add(new Node23034(a,b,c));
			pq.add(new Node23034(b,a,c));
		}
		
		//최소 신장 트리 생성
		int cost = kruskal();
		
		int q = Integer.parseInt(br.readLine());
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			//최소 신장 트리의 길이에서 두 팀장간의 연결된 간선 중 최대 비용을 제외
			sb.append(cost-bfs(s,e)).append('\n');
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	
	//root를 찾아 출력
	public static int find(int node) {
		if(parents[node]==node)
			return node;
		return parents[node] = find(parents[node]);
	}
	
	public static int kruskal() {
		int cnt=0;
		int cost=0;
		
		while(!pq.isEmpty()) {
			Node23034 now = pq.poll();
			
			int from = now.from;
			int to = now.to;
			int w = now.w;
			
			if(find(from) != find(to)) {
				parents[find(from)] = find(to);
				cost+=w;
				//최소 신장 트리에 대한 정보를 list에 입력
				list[from].add(new Node23034(from,to,w));
				list[to].add(new Node23034(to,from,w));
				if(++cnt==n-1)
					return cost;
			}
			
		}
		return cost;
	}
	
	//최소 신장 트리에서 시작점부터 도착점까지 이어지는 간선 중 가장 큰 간선의 비용을 구한다.
	public static int bfs(int s, int e) {
		boolean[] flag = new boolean[n+1];
		
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {s, 0});
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int from = now[0];
			int w = now[1];
			
			if(flag[from])
				continue;
			if(from==e)
				return w;
			flag[from] = true;
			
			for(Node23034 next : list[from]) {
				int to = next.to;
				if(flag[to])
					continue;
				que.add(new int[] {to,Math.max(w, next.w)});
			}
		}
		
		//나오면 안되는 값이기에 이상치를 출력하도록 설정
		return -987654321;
	}

}