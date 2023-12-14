import java.io.*;

public class Main {
	
	public static int n;
	public static long[] tree;
	public static long[] treeCnt;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		int k = (int) Math.ceil(Math.log(200000)/Math.log(2)) + 1;
		int size = (int) Math.pow(2, k);
		tree = new long[size];
		treeCnt = new long[size];
		//첫 번째 나무는 비용없이 심음
		int first = Integer.parseInt(br.readLine());
		updateSum(0,200000,1,first);
		updateCnt(0,200000,1,first);
		long cost = 1;
		for(int i = 1; i<n; i++) {
			int value = Integer.parseInt(br.readLine());
			long left = value*queryCnt(0,200000,1,0,value-1) - querySum(0,200000,1,0,value-1);
			long right = querySum(0,200000,1,value+1,200000) - value*queryCnt(0,200000,1,value+1,200000);
			cost = (left+right)%1000000007 * cost%1000000007; 
			
			updateSum(0,200000,1,value);
			updateCnt(0,200000,1,value);
		}
		
		bw.write(String.valueOf(cost));
		bw.close();
		br.close();
		
	}
	
	public static void updateSum(int s, int e, int node, long value) {
		if(value<s || e<value)
			return;
		
		tree[node] += value;
		if(s==e)
			return;
		
		int mid = (s+e) >>> 1;
		
		updateSum(s,mid,node*2,value);
		updateSum(mid+1,e,node*2+1,value);
	}
	
	public static void updateCnt(int s, int e, int node, long value) {
		if(value<s || e<value)
			return;
		
		treeCnt[node] += 1;
		if(s==e)
			return;
		
		int mid = (s+e) >>> 1;
		
		updateCnt(s,mid,node*2,value);
		updateCnt(mid+1,e,node*2+1,value);
	}
	
	public static long querySum(int s, int e, int node, int left, int right) {
		if(e<left || right<s)
			return 0;
		if(left<=s && e<=right)
			return tree[node];
		
		int mid = (s+e) >>> 1;
		
		return querySum(s,mid,node*2,left,right) + querySum(mid+1,e,node*2+1,left,right);
	}
	
	public static long queryCnt(int s, int e, int node, int left, int right) {
		if(e<left || right<s)
			return 0;
		if(left<=s && e<=right) {
			return treeCnt[node];
		}
		int mid = (s+e) >>> 1;
		
		return queryCnt(s,mid,node*2,left,right) + queryCnt(mid+1,e,node*2+1,left,right);
	}
	
}