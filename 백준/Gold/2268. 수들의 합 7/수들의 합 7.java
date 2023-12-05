import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] arr;
	public static long[] tree; 

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int k = (int) Math.ceil(Math.log(N)/Math.log(2)) + 1;
		int size = (int) Math.pow(2, k);
		
		arr = new int[N+1];
		tree = new long[size];
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==1) {
				int diff = c-arr[b];
				arr[b] = c;
				update(1,N,1,b,diff);
			}else {
				if(b>c) {
					int temp = c;
					c = b;
					b = temp;
				}
				sb.append(query(1,N,1,b,c)).append('\n');
			}
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	/*
	 * s: 탐색하는 리프노드의 시작
	 * e: 탐색하는 리프노드의 끝
	 * node: 현재 세그먼트 트리의 노드
	 * target: 수정하고자 하는 목표 리프노드
	 * diff: 이전 값과의 차이
	 */
	public static void update(int s, int e, int node, int target, int diff) {
		//세그먼트 트리 노드에 포함된 리프노드가 target을 포함하지 않을 경우 update하지 않음
		if(target<s || e<target)
			return;
		
		//리프노드에 도달한 경우 해당 node값을 update
		if(s==e) {
			tree[node] = arr[target];
			return;
		}
		
		//target을 포함한 노드의 경우 차이값을 update
		tree[node] += diff;
		
		int mid = (s+e) >>> 1;
		//왼쪽 자식과 오른쪽 자식으로 나누어 탐색, 완전 이진 트리의 특성상 한쪽은 target을 포함하지 않음
		update(s,mid,node*2,target,diff);
		update(mid+1,e,node*2+1,target,diff);
	}
	
	/*
	 * s: 탐색하는 리프노드의 시작
	 * e: 탐색하는 리프노드의 끝
	 * node: 현재 세그먼트 트리의 노드
	 * L: 합을 구하고 싶은 리프노드의 시작
	 * R: 합을 구하고 싶은 리프노드의 끝
	 */
	public static long query(int s, int e, int node, int L, int R) {
		//구간을 벗어나는 리프노드의 경우 0을 리턴
		if(e<L || R<s)
			return 0;
		//구간에 완전히 포함되는 리프노드의 경우 해당 tree값을 리턴
		if(L<=s && e<=R)
			return tree[node];
		
		//일부만 포함된 경우 양쪽을 나누어 탐색
		int mid = (s+e) >>> 1;
		return query(s,mid,node*2,L,R) + query(mid+1,e,node*2+1,L,R);
	}

}