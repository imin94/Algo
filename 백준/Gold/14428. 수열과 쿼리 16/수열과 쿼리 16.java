import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] arr, tree;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		//완전 이진 트리의 세그먼트 트리 사이즈 구하기
		int k = (int) Math.ceil(Math.log(n)/Math.log(2)) + 1;
		int size = (int) Math.pow(2, k);
		
		//입력값을 저장할 배열 arr(세그먼트 트리의 리프 노드 값)와 세그먼트 트리 생성
		arr = new int[n+1];	//0번 인덱스는 사용하지 않음
		tree = new int[size];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		init(1,n,1);
		
		int m = Integer.parseInt(br.readLine());
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==1) {
				arr[b]=c;
				update(1,n,1,b,c);
			}
			else
				sb.append(query(1,n,1,b,c)).append('\n');
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	
	/*
	 * 세그먼트 트리의 노드는 자식들의 노드에 포함된 리프노드의 인덱스의 값이 가장 작은 인덱스가 출력됨
	 * s: 탐색하는 리프노드의 시작 범위
	 * e: 탐색하는 리프노드의 끝 범위
	 * node: 현재 세그먼트 트리 노드
	 */
	public static int init(int s, int e, int node) {
		if(s==e)
			return tree[node]=s;
		
		int mid = (s+e) >>> 1;
		
		//왼쪽 자식이 가지고 있는 인덱스(포함된 리프 노드들 중 값이 가장 작은 인덱스)와 오른쪽 인덱스가 가지고 있는 인덱스 값을 비교
		//더 작은 값을 가지고 있는 인덱스를 node읙 값으로 설정
		if(arr[init(s,mid,node*2)]<=arr[init(mid+1,e,node*2+1)])
			return tree[node] = tree[node*2];
		return tree[node] = tree[node*2+1];
	}
	
	/*
	 * s: 탐색하는 리프노드의 시작 범위
	 * e: 탐색하는 리프노드의 끝 범위
	 * node: 현재 세그먼트 트리 노드
	 * target: 변경하고자 하는 리프노드의 인덱스
	 * value: 변경값
	 */
	public static void update(int s, int e, int node, int target, int value) {
		if(target<s || e<target)
			return;
		
		if(s==e)
			return;
			
		int mid = (s+e) >>> 1;
		update(s,mid,node*2,target,value);
		update(mid+1,e,node*2+1,target,value);
		
		if(arr[tree[node*2]]<=arr[tree[node*2+1]])
			tree[node] = tree[node*2];
		else
			tree[node] = tree[node*2+1];
	}
	
	/*
	 * s: 탐색하는 리프노드의 시작 범위
	 * e: 탐색하는 리프노드의 끝 범위
	 * node: 현재 세그먼트 트리 노드
	 * L: target 리프노드의 시작 범위
	 * R: target 리프노드의 끝 범위
	 */
	public static int query(int s, int e, int node, int L, int R) {
		//target 범위를 벗어나는 경우 메서드 종료
		if(e<L || R<s)
			return -1;
		
		//target 범위에 완전히 들어오는 경우 해당 세그먼트 트리 노드 값 출력
		if(L<=s && e<=R)
			return tree[node];
		
		int mid = (s+e) >>> 1;
		int left = query(s,mid,node*2,L,R);
		int right = query(mid+1,e,node*2+1,L,R);
		
		if(left == -1)
			left = right;
		if(right == -1)
			right = left;
		
		//왼쪽 자식과 오릉쪽 자식의 범위가 나뉘어지는 경우
		if(arr[left]<=arr[right])
			return left;
		return right;
	}

}