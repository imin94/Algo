import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//빼빼로를 많이 가져갈 수 있도록 오름차순정렬
class Node13905 implements Comparable<Node13905>{
	int to;
	int w;
	int min;
	
	public Node13905(int to, int w) {
		this.to = to;
		this.w = w;
	}
	
	public Node13905(int to, int w, int min) {
		this.to = to;
		this.w = w;
		this.min = min;
	}
	
	@Override
	public int compareTo(Node13905 o) {
		return o.w-w;
	}
}

public class Main {
	
	public static int n, s, e;
	public static List<Node13905>[] list;

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
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node13905(b,c));
			list[b].add(new Node13905(a,c));
		}
		
		bw.write(String.valueOf(prim()));
		br.close();
		bw.close();
	}
	
	public static int prim() {
		boolean[] flag = new boolean[n+1];
		Queue<Node13905> pq = new PriorityQueue<>();
		//임의의 노드에서 가중치 없이 시작
		pq.add(new Node13905(s,0,987654321));
		
		while(!pq.isEmpty()) {
			Node13905 now = pq.poll();
			int to = now.to;
			int w = now.w;
						
			if(flag[to])
				continue;
			flag[to] = true;
			
			if(to == e)
				return now.min;
			
			for(Node13905 next : list[to]) {
				if(flag[next.to])
					continue;
				pq.add(new Node13905(next.to,next.w,Math.min(next.w, now.min)));
			}
		}
		return 0;
	}
}