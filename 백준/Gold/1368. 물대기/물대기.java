import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node1368 implements Comparable<Node1368>{
	
	int from;
	int to;
	int w;
	
	public Node1368(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}

	@Override
	public int compareTo(Node1368 o) {
		return w-o.w;
	}
	
}

public class Main {
	
	public static int n;
	public static int[] well;
	public static int[] parents;
	public static Queue<Node1368> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		well = new int[n+1];	//i번째 논에 우물을 파는 비용
		parents = new int[n+1];	//논마다의 root
		
		for(int i = 1; i<=n; i++) {
			well[i] = Integer.parseInt(br.readLine());
			parents[i] = i;		//논마다 자기 자신을 default root로 가짐, 0번째 가상의 논은 default인 0 유지
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				int w = Integer.parseInt(st.nextToken());
				//중복되는 자료는 보지 않음
				if(i<j)
					continue;
				//자기 자신에게 우물을 파는 비용을 0번 인덱스를 활용해 가상의 논을 만들어줌
				else if(i==j)
					pq.add(new Node1368(0, i, well[i]));
				else
					pq.add(new Node1368(i, j, w));
			}
		}
		
		bw.write(String.valueOf(kruskal()));
		br.close();
		bw.close();
	}
	
	//root 찾기
	public static int find(int node) {
		if(parents[node] == node)
			return node;
		//root가 갱신되는 것을 계속해서 업데이트 해주기 위해 parents[node]의 값도 변경
		return parents[node] = find(parents[node]);
	}
	
	public static int kruskal() {
		int cost = 0;
		
		while(!pq.isEmpty()) {
			Node1368 now = pq.poll();
			int from = now.from;
			int to = now.to;
			
			if(find(from) != find(to)) {
				parents[find(from)] = find(to);
				cost+=now.w;
			}
		}
		
		return cost;
	}
	
}