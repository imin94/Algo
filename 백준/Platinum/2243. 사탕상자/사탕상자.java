import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] tree;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int k = (int) Math.ceil(Math.log(1000000)/Math.log(2)) + 1;
		int size = (int) Math.pow(2, k);
		tree = new int[size];
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == 2) {
				int c = Integer.parseInt(st.nextToken());
				update(1,1000000,1,b,c);
			}
			else
				sb.append(query(1,1000000,1,b)).append('\n');
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	
	public static void update(int s, int e, int node, int target, int value) {
		
		if(target<s || e<target)
			return;
		
		tree[node] += value;
		if(s==e) 
			return;
		
		int mid = (s+e) >>> 1;
		update(s,mid,node*2,target,value);
		update(mid+1,e,node*2+1,target,value);
			
	}
	
	public static int query(int s, int e, int node, int target) {
		
		if(s==e) {
			update(1,1000000,1,s,-1);
			return s;
		}
		
		int mid = (s+e) >>> 1;
		
		if(tree[node*2]>=target)
			return query(s,mid,node*2,target);
		else
			return query(mid+1,e,node*2+1,target-tree[node*2]);
			
	}

}