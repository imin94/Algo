import java.io.*;

public class Main {
	
	public static int[] tree;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		int k = (int) Math.ceil(Math.log(20001)/Math.log(2)) + 1;
		int size = (int) Math.pow(2, k);
		
		tree = new int[size];
		
		for(int i = 1; i<=n; i++) {
			int num = Integer.parseInt(br.readLine());
			update(1,20001,1,num+10001);
			query(1,20001,1,(i+1)/2);
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	
	public static void update(int s, int e, int node, int num) {
		if(num<s || e<num)
			return;
		tree[node]++;
		if(s==e)
			return;
		
		int mid = (s+e) >>> 1;
		
		update(s,mid,node*2,num);
		update(mid+1,e,node*2+1,num);
	}
	
	public static void query(int s, int e, int node, int idx) {
		if(s==e) {
			sb.append(s-10001).append('\n');
			return;
		}
		
		int mid = (s+e) >>> 1;
		if(idx<=tree[node*2])
			query(s,mid,node*2,idx);
		else
			query(mid+1,e,node*2+1,idx-tree[node*2]);
		
	}

}