import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node10423 implements Comparable<Node10423>{
	int from;
	int to;
	int w;
	
	public Node10423(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node10423 o) {
		return this.w - o.w; 
	}
	
}

public class Main {
	
	public static int N;
	public static int sum;
	public static int[] p;
	public static boolean[] flag;
	public static Queue<Node10423> que = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K= Integer.parseInt(st.nextToken());
		
		//발전소가 있는 도시 방문처리
		flag = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<K; i++) {
			int idx = Integer.parseInt(st.nextToken());
			flag[idx] = true;
		}
		
		p = new int[N+1];
		for(int i = 1; i<=N; i++) {
			if(flag[i])
				p[i] = -1;
			else
				p[i] = i;
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			que.add(new Node10423(a,b,w));
		}
		
		while(!que.isEmpty()) {
			Node10423 node = que.poll();
			if(find(node.from) != find(node.to)) {
				union(node.from,node.to);
				sum+=node.w;
			}
		}
		
		bw.write(String.valueOf(sum));
		br.close();
		bw.close();
	}
	
	//공통 부모 찾기
	public static int find(int idx) {
		if(p[idx]==-1)
			return -1;
		if(p[idx]==idx)
			return idx;
		else
			return p[idx]=find(p[idx]);	
	}
	
	//부모가 다른 경우 합집합
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==-1)
			p[b] = a;
		else if(b==-1)
			p[a] = b;
		else if(a!=b)
			p[b] = a;
	}

}