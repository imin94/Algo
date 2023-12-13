import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node21924 implements Comparable<Node21924> {
	
	int to;
	int w;
	
	public Node21924(int to, int w) {
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node21924 o) {
		return w-o.w;
	}
	
}

public class Main {
	
	public static int n;
	public static long sumCost;
	public static List<Node21924>[] list;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		
		for(int i = 1; i<=n; i++) {
			list[i] = new ArrayList<Node21924>();
		}
		
		sumCost = 0;
		//인접리스트 생성
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			//모든 도로를 설치하는데 발생하는 비용
			sumCost+=(long)c;
			list[a].add(new Node21924(b,c));
			list[b].add(new Node21924(a,c));
		}
		
		bw.write(String.valueOf(prim()));
		br.close();
		bw.close();
	}
	
	public static long prim() {
		Queue<Node21924> pq = new PriorityQueue<>();
		pq.add(new Node21924(1,0));
		
		boolean[] flag = new boolean[n+1];
		long cost = 0;
		int cnt = 0;
		
		//연결 리스트에서 최소 비용의 간선을 하나씩 연결
		while(!pq.isEmpty()) {
			Node21924 now = pq.poll();
			
			int from = now.to;
			int w = now.w;
			if(flag[from])
				continue;
			flag[from] = true;
			cost += (long) w;
			if(cnt++ == n-1)
				return sumCost-cost;
			
			for(Node21924 next : list[from]) {
				int to = next.to;
				if(flag[to])
					continue;
				pq.add(next);
			}
		}
		
		//모두 연결되지 않는다면 -1 출력
		return -1;
	}

}