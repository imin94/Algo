import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class node1197 implements Comparable<node1197>{
	
	int to;
	int weight;
	
	public node1197(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(node1197 o) {
		return this.weight-o.weight;
	}
	
}

public class Main {
	
	public static int sum;
	public static boolean[] flag;
	public static List<node1197>[] tree;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		sum = 0;
		tree = new ArrayList[n+1];
		flag = new boolean[n+1];
		
		for(int i = 1; i<=n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			tree[a].add(new node1197(b,c));
			tree[b].add(new node1197(a,c));
		}
		
		prim(1);
		
		bw.write(String.valueOf(sum));
		br.close();
		bw.close();
		
	}
	
	public static void prim(int i) {
		Queue<node1197> que = new PriorityQueue<>();
		que.add(new node1197(i,0));
		
		while(!que.isEmpty()) {
			node1197 temp = que.poll();
			int to = temp.to;
			int weight = temp.weight;
			if(flag[to])
				continue;
			flag[to]=true;
			sum+=weight;

			for(node1197 node : tree[to]) {
				if(flag[node.to])
					continue;
				que.add(node);
			}
		}
		
	}

}