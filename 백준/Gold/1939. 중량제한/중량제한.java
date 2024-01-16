import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node1939 {
	int to;
	int w;

	public Node1939(int to, int w) {
		this.to = to;
		this.w = w;
	}
	
}

public class Main {
	
	public static int n,s,e;
	public static List<Node1939>[] list;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i = 1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		//인접리스트 생성
		int minCost = Integer.MAX_VALUE;
		int maxCost = 0;	
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			minCost = Math.min(minCost, c);
			maxCost = Math.max(maxCost, c);
			list[a].add(new Node1939(b,c));
			list[b].add(new Node1939(a,c));
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		while(minCost<=maxCost) {
			int mid = (minCost+maxCost) >>> 1;
			//mid에 대해 통과 가능하면 mid를 min값으로 다시 이분탐색
			if(bfs(mid))
				minCost = mid+1;
			//mid에 대해 통과하지 못한다면 max 값을 mid-1로 다시 이분탐색
			else
				maxCost = mid-1;
		}
		bw.write(String.valueOf(maxCost));
		br.close();
		bw.close();
		
	}
	
	public static boolean bfs(int cost) {
		boolean[] flag = new boolean[n+1];

		Queue<Node1939> que = new LinkedList<>();
		que.add(new Node1939(s,cost));
		
		
		while(!que.isEmpty()) {
			Node1939 now = que.poll();
			int from = now.to;
			
			//이미 방문한 다리인 경우 continue;
			if(flag[from])
				continue;
			flag[from] = true;
			
			//도착지에 도착한 경우 true 반환
			if(from == e)
				return true;
			
			for(Node1939 next : list[from]) {
				int to = next.to;
				int nextW = next.w;
				if(flag[to] || cost>nextW)
					continue;
				
				que.add(new Node1939(to,nextW));
			}
		}
		
		//다 지나도 도착지에 도달하지 못한 경우 false 반환
		return false;
	}

}