import java.io.*;
import java.util.StringTokenizer;

public class Main {
    
    public static int[] tree, lazy;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int k = (int) Math.ceil(Math.log(n)/Math.log(2)) + 1;
        int size = (int) Math.pow(2, k);
        //세그먼트 트리
        tree = new int[size];
        //업데이트를 즉각적으로 반영하지 않고 필요할 때 진행하도록 누적할 수 있는 lazy배열을 생성
        lazy = new int[size];
        
        arr = new int[n+1];
        for(int i = 0 ; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            if(a==0)
            	turn(1,n,1,b,c);
            else
            	sb.append(query(1,n,1,b,c)).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
    
    //waitUpdate: 쌓아 놓은 값을 변경하고 아래 자식 노드들에게 변경할 값을 누적하는 메서드
    public static void waitUpdate(int s, int e, int node) {
    	//쌓인 변경이 없다면(혹은 짝수번 쌓여서 변경할게 없거나) 동작하지 않음
    	if(lazy[node] % 2 == 1) {
    		//리프노드가 아니라면 자식 노드들에게 해당 변경 값만큼 누적
    		if(s != e) {
    			lazy[node*2] += lazy[node];
    			lazy[node*2+1] += lazy[node];    			
    		}
    		//업데이트가 있다면, 해당 노드의 전체 전구에서 켜져있는 전구의 개수를 빼주어 반전시킨다
    		tree[node] = (e-s+1) - tree[node];
    		lazy[node] = 0;
    	}
    }
    
    public static void turn(int s, int e, int node, int L, int R) {
    	//해당 노드에 업데이트가 필요한 경우 진행함
    	waitUpdate(s,e,node);
    	//탐색 범위를 벗어나는 리프 노드의 경우 더 이상 진행하지 않음
    	if(e<L || R<s)
    		return;
    	//탐색 범위 안에 완전히 들어오는 리프 노드의 경우 waitUpdate를 진행
        if(L <= s && e <= R) {
        	lazy[node] = 1;
        	waitUpdate(s,e,node);
        	return;
        }
        
        //탐색 범위를 일부만 들어오는 리프 노드의 경우 mid를 기준으로 나누어 재탐색
        int mid = (s+e) >>> 1;
        turn(s,mid,node*2,L,R);
        turn(mid+1,e,node*2+1,L,R);
        tree[node] = tree[node*2] + tree[node*2+1];
    }
    
    public static int query(int s, int e, int node, int L, int R) {
    	//결과를 조회하는 node에 update가 이루어지지 않은 곳이 있다면 update 후 결과 취합 
    	waitUpdate(s,e,node);
    	if(e<L || R<s)
    		return 0;
    	if(L<=s && e<=R) {
    		return tree[node];
    	}
    	int mid = (s+e) >>> 1;
    	return query(s,mid,node*2,L,R)+query(mid+1,e,node*2+1,L,R);   		
    }

}