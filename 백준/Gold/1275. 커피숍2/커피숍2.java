import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static long[] nums;
	public static long[] tree;	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		//세그먼트 트리의 사이즈는 2^k>=n을 만족시키는 정수 k를 구한 후 2^(k+1)한 값이됨
		int k = (int) Math.ceil(Math.log(n)/Math.log(2)) + 1;
		int size = (int) Math.pow(2, k);
		
		nums = new long[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		tree = new long[size];
		
		//세그먼트 트리에 값을 입력
		init(1,n,1);
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if(a<=b)
				sb.append(query(1,n,1,a,b));
			else
				sb.append(query(1,n,1,b,a));
			long diff = d-nums[c];
			nums[c] = d;
			update(1,n,1,c,diff);
			sb.append('\n');
		}
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	//루트 노드(tree배열의 idx = 1)로 부터 왼쪽 자식이 nums 배열의 mid 까지, 오른쪽 자식이 nums+1이후의 값을 포함하고 있다
	//이러한 성질을 이용해 mid를 기점으로 리프노드에 도달 할 때 까지 구간을 나누어 주며,
	//리프 노드의 값을 제외한 노드들은 자식 노드의 합을 가져 구간합을 구하기 용이하도록 함
	public static long init(int s, int e, int node) {
		if(s==e)
			return tree[node] = nums[s];
		
		int mid = (s+e) >>> 1;
		
		return tree[node] = init(s,mid,node*2) + init(mid+1,e,node*2+1);
	}
	
	//업데이트 메서드
	public static void update(int s, int e, int node, int target, long diff) {
		if(target<s || e<target)
			return;
		tree[node] += diff;
		if(s==e)
			return;
		int mid = (s+e) >>> 1;
		
		update(s,mid,node*2,target,diff);
		update(mid+1,e,node*2+1,target,diff);
	}
	
	//구간합 메서드
	public static long query(int s, int e, int node, int L, int R) {
		if(R<s || e<L)
			return 0;
		if(L<=s && e<=R)
			return tree[node];
		
		int mid = (s+e) >>> 1;
		
		return query(s,mid,node*2,L,R) + query(mid+1,e,node*2+1,L,R);
	}

}