import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] arr,tree;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//0도부터 65535도로 총 65536개의 인덱스가 필요
		int k = (int) Math.ceil(Math.log(65536)/Math.log(2)) + 1;
		int size = (int) Math.pow(2, k);
		
		arr = new int[N+1];
		tree = new int[size];
		
		//0도부터 65535도 까지 리프노드 업데이트, K-1번 업데이트 진행
		for(int i = 1; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			update(0,65535,1,arr[i],1);
		}
		
		long sum = 0;
		//K번째부터 중앙값 온도 측정 시작
		for(int i = K; i<=N; i++) {
			//0도부터 65535도 까지 리프노드 업데이트 후 온도 출력, i-K+1번째 업데이트 했던 온도 삭제
			arr[i] = Integer.parseInt(br.readLine());
			update(0,65535,1,arr[i],1);
			sum += query(0,65535,1,(K+1)/2);
			update(0,65535,1,arr[i-K+1],-1);
		}
		
		bw.write(String.valueOf(sum));
		br.close();
		bw.close();
		
	}
	
	public static void update(int s, int e, int node, int idx, int value) {
		
		if(idx<s || e<idx)
			return;
		
		tree[node] += value;
		
		if(s==e)
			return;
		
		int mid = (s+e) >>> 1;
		
		update(s, mid, node*2, idx, value);
		update(mid+1, e, node*2+1, idx, value);
		
	}
	
	public static int query(int s, int e, int node, int median) {

		if(s==e)
			return s;
		
		int mid = (s+e) >>> 1;
		
		if(tree[node*2]>=median)
			return query(s,mid,node*2,median);
		return query(mid+1,e,node*2+1,median-tree[node*2]);
		
	}

}