import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node20010 implements Comparable<Node20010>{
	int from, to, w;

	public Node20010(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	
	public Node20010(int to, int w) {
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node20010 o){
		return w-o.w;
	}
	
}

public class Main {
	
	public static int n, maxIdx;
	public static long max = 0;
	public static int[] parents;
	public static boolean[] flag;
	public static Queue<Node20010> pq = new PriorityQueue<>();
	public static List<Node20010>[] list;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//각 노드는 자기 자신을 뿌리로 시작함(각 노드가 연결되어있지 않음을 의미)
		parents = new int[n];
		for(int i = 0; i<n; i++)
			parents[i] = i;
		
		//MST의 정보가 들어갈 인접리스트 생성
		list = new ArrayList[n];
		for(int i = 0; i<n; i++)
			list[i] = new ArrayList<>();
		
		//간선들의 정보를 가중치 오름차순으로 pq에 넣음
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pq.add(new Node20010(from,to,w));
		}
		
		//MST 비용과 인접리스트 생성
		sb.append(kruskal()).append('\n');
		
		//임의의 노드(0에서 시작)로 부터 가장 먼 노드의 위치를 구함
		flag = new boolean[n+1];
		flag[0] = true;
		dfs(0,0);
		
		//maxIdx에 0번 노드로부터 가장 먼 노드의 위치가 저장되었기에 해당 노드로 부터 다시 한 번 dfs를 시행해 가장 긴 간선의 정보를 max에 넣음
		max = 0;
		flag = new boolean[n+1];
		flag[maxIdx] = true;
		dfs(maxIdx,0);
		
		sb.append(max);
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	//연결된 그래프의 뿌리 찾기
	public static int find(int node) {
		if(parents[node]==node)
			return node;
		return parents[node] = find(parents[node]);
	}
	
	//MST 비용과 인접리스트 구하기
	public static long kruskal() {
		//MST 비용 
		long cost = 0;
		//연결된 노드의 개수를 count해 n-1개와 동일해지면 break로 최적화
		int cnt = 0;
		while(!pq.isEmpty()) {
			Node20010 now = pq.poll();
			int from = now.from;
			int to = now.to;
			int w = now.w;
			
			int a = find(from);
			int b = find(to);
			if(a == b)
				continue;
			parents[a] = b;
			cost += (long) w;
			//MST 인접리스트 생성
			list[from].add(new Node20010(to,w));
			list[to].add(new Node20010(from,w));
			if(++cnt == n-1)
				break;
		}
		
		return cost;
	}
	
	//가장 긴(비용이 가장 비싼) 이어진 간선 찾기 
	public static void dfs(int s, long cost) {
		if(max<cost) {
			max = cost;
			maxIdx = s;
		}
		
		for(Node20010 next : list[s]) {
			int to = next.to;
			//방문한 곳이라면 continue(돌아가는 것을 방지)
			if(flag[to])
				continue;
			flag[to] = true;
			
			dfs(to,cost+next.w);
		}
	}

}