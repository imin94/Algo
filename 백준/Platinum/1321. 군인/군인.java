import java.io.*;
import java.util.StringTokenizer;

public class Main {
    
    public static int[] arr;
    public static long[] tree;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //부대별 병사의 수, 0번 부대는 dafault 값인 0으로 유지한다
        arr = new int[N+1];
        for(int i = 1; i<=N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        
        //세그먼트 트리를 만들기 위한 size 계산, 2^k>=N을 만족하는 정수 k의 +1을 한 값으로 트리를 생성
        int k = (int) Math.ceil(Math.log(N)/Math.log(2)) + 1;
        int size = (int) Math.pow(2, k);
        
        tree = new long[size];
        
        //이분 탐색으로 각 부대의 인원 수 값을 세그먼트 트리 하단에 채우며, 상위 노드에는 각 부대의 합을 기재한다
        init(1,N,1);
        
        //M개의 명령을 받아 수행
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            //명령이 1인 경우 b부대의 인원을 c만큼 수정
            if(a == 1) {
            	int c = Integer.parseInt(st.nextToken());
                update(1,N,b,1,c);
            }
            //명령이 2인 경우 b번째 군인이 어느 부대에 속해있는지 출력
            if(a == 2)
            	sb.append(find(1,N,1,b)).append('\n');
        }
        
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
    
    /*
     * s: 첫 부대
     * e: 마지막 부대
     * node: root가 1번 이며, 완전 이진 트리이므로 왼쪽 자식이 node*2, 오른쪽 자식이 node*2 + 1
     * 부모 노드는 자식 노드들의 합을 나타냄
     * root 노드는 s번부터 e번 부대 까지의 합을 나타내고 있으며, root의 왼쪽 자식은 s번부터 mid번, 오른쪽 자식은 mid+1번부터 e번 부대까지의 합을 나타낸다
     * 이러한 완전 이진 트리와 이분 탐색의 특성을 이용해 부대 별 node를 찾는다
     */
    public static long init(int s, int e, int node) {
        
        if(s==e)
            return tree[node] = arr[s];
        
        int mid = (s+e) >>> 1;
        return tree[node] = init(s,mid,node*2) + init(mid+1,e,node*2+1);
    }
    
    
    /*
     * s,e,node는 위와 같음
     * idx: 인원수를 변경하고자하는 부대
     * value: 변경하고자 하는 인원 수
     * idx에 해당하는 부대의 인원을 value만큼 더하고 뺀다
     */
    public static void update(int s, int e, int idx, int node, long value) {
        if(idx<s || idx>e)
            return;
        
        tree[node] += value;
        
        if(s==e)
            return;
        
        int mid = (s+e) >>> 1;
        
        update(s,mid,idx,node*2,value);
        update(mid+1,e,idx,node*2+1,value);
    }
    
    public static int find(int s, int e, int node, long value) {
    	if(s==e)
    		return s;
    	int mid = (s+e) >>> 1;
    	//찾으려는 군인의 번호보다 왼쪽 자식 노드의 값이 크다면, 왼쪽 자식 노드 아래의 부대 안에 군인이 있어 왼쪽 자식 노드로 탐색
    	if(tree[node*2]>=value)
    		return find(s,mid,node*2,value);
    	//찾으려는 군인의 번호가 왼쪽 자식의 노드의 값보다 크다면, 오른쪽 자식 노드 아래의 부대 안에 군인이 있어 오른쪽 자식 노드로 탐색
    	//이때 왼쪽 자식 노드의 값 만큼 군인의 번호를 제외하고 오른쪽 자식 노드에서 탐색
    	else
    		return find(mid+1,e,node*2+1,value-tree[node*2]);
    }

}