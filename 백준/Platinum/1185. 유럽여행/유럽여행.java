import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node1185 implements Comparable<Node1185> {
	
	int to;
	int w;
	
	public Node1185(int to, int w) {
		this.to = to;
		this.w = w;
	}

	@Override
	public int compareTo(Node1185 o) {
		return this.w - o.w;
	}
	
}

public class Main {
	
	public static int n;
	public static List<Node1185>[] list;
	public static int[] visitCost;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		visitCost = new int[n+1];
		
		//방문 금액이 가장적은 도시
		int min = 987654321;
		for(int i = 1; i<=n; i++) {
			list[i] = new ArrayList<>();
			int cost = Integer.parseInt(br.readLine());
			visitCost[i] = cost;
			min = Math.min(min, cost);
		}
		
		//인접리스트 생성
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			//최ㅗ 신장 트리이며, 첫 시작 점으로 다시 돌아와야 하므로 한번의 도로에 도착 도시 시작 도시의 비용 및 도로비*2를 계산한다
			list[a].add(new Node1185(b,visitCost[a]+visitCost[b]+2*c));
			list[b].add(new Node1185(a,visitCost[a]+visitCost[b]+2*c));
			
		}
		
		//최소 신장 트리에 시작 도시의 비용을 더한다
		bw.write(String.valueOf(prim()+min));
		br.close();
		bw.close();
		
	}
	
	public static int prim() {
		int cost = 0;
		Queue<Node1185> pq = new PriorityQueue<>();
		boolean[] flag = new boolean[n+1];
		//임의의 도시를 시작으로 하며, 가중치는 0으로 계싼
		pq.add(new Node1185(1,0));
		
		while(!pq.isEmpty()) {
			Node1185 now = pq.poll();
			int from = now.to;
			
			if(flag[from])
				continue;
			
			flag[from] = true;
			cost += now.w;
			
			for(Node1185 next : list[from]) {
				
				if(flag[next.to])
					continue;
				
				pq.add(next);
			}
			
		}
		
		return cost;
	}

}