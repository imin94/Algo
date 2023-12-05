import java.io.*;

public class Main {
	
	public static int[] tree, arr;
	public static int n;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));		
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1];
		
		for(int i = 1; i<=n; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		int k = (int) Math.ceil(Math.log(n)/Math.log(2)) + 1;
		int size = (int) Math.pow(2, k);
		
		tree = new int[size];
		
		init(1,n,1);
		bw.write(String.valueOf(getMax(1,n)));
		br.close();
		bw.close();
		
	}
	
	/*
	 * 자식들 중 높이가 낮은 인덱스를 출력하는 세그먼트 트리
	 * s: 탐색하고자 하는 리프 노드의 시작
	 * e: 탐색하고자 하는 리프 노드의 끝
	 * node: 현재 세그먼트 트리 노드
	 */
	public static int init(int s, int e, int node) {
		//리프 노드에 도달한 경우 해당 리프노드의 인덱스를 기재
		if(s==e)
			return tree[node] = s;
		//완전 이진 트리의 특성을 이용해 mid를 중점으로 왼쪽 자식이 s~mid, 오른쪽 자식이 mid+1~e
		int mid = (s+e) >>> 1;
		
		//왼쪽 자식 중 높이가 가장 낮은 리프 노드와 오른쪽 자식 중 높이가 가장 낮은 리프 노드를 비교해 더 낮은 리프 노드의 인덱스를 리턴
		if(arr[init(s,mid,node*2)]<=arr[init(mid+1,e,node*2+1)])
			return tree[node] = tree[node*2];
		else
			return tree[node] = tree[node*2+1];
		
	}
	
	/*
	 * target 범위 중 높이가 가장 낮은 인덱스를 호출
	 * s: 탐색하고자 하는 리프 노드의 시작
	 * e: 탐색하고자 하는 리프 노드의 끝
	 * node: 현재 세그먼트 트리 노드
	 * L: target 리프 노드의 시작
	 * R: target 리프 노드의 끝
	 */
	public static int minIdx(int s, int e, int node, int L, int R) {
		if(e<L || R<s)
			return -1;
		if(L<=s && e<=R)
			return tree[node];
		
		int mid = (s+e) >>> 1;
		
		int left = minIdx(s,mid,node*2,L,R);
		int right = minIdx(mid+1,e,node*2+1,L,R);
		
		if(left == -1)
			left = right;
		if(right == -1)
			right = left;
		
		if(arr[left]<=arr[right])
			return left;
		else
			return right;
	}
	
	/*
	 * 분할 정복 메서드
	 * L: target 리프 노드의 시작
	 * R: target 리프 노드의 끝
	 */
	public static long getMax(int L, int R) {
		int idx = minIdx(1,n,1,L,R);
		long space = (long)(R-L+1)*(long)arr[idx];
		
		if(L<idx) {
			long left = getMax(L,idx-1);
			space = (long) Math.max(space, left);
		}
		if(idx<R) {
			long right = getMax(idx+1,R);
			space = (long) Math.max(space, right);
		}
		return space;
	}
	
}