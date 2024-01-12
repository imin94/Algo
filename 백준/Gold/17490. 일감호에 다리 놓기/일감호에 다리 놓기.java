import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node17490 implements Comparable<Node17490>{
	int from, w;

	public Node17490(int from, int w) {
		this.from = from;
		this.w = w;
	}
	
	@Override
	public int compareTo(Node17490 o) {
		return w-o.w;
	}
}

public class Main {
	
	public static int n, m;
	public static long k;
	public static int[] parents;
	public static Queue<Node17490> pq = new PriorityQueue<>();
	public static List<Integer>[] list; 

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Long.parseLong(st.nextToken());
		
		parents = new int[n+1];
		list = new ArrayList[n+1];
		for(int i = 1; i<=n; i++) {
			list[i] = new ArrayList<>();
			if(i<n)
				list[i].add(i+1);
			
			//각자 노드는 자기 자신이 root
			parents[i] = i;
		}
		list[1].add(n);
		
		st = new StringTokenizer(br.readLine());
		//모든 원이 이어져있는 인접리스트 생성
		for(int i = 1; i<=n; i++)
			pq.add(new Node17490(i,Integer.parseInt(st.nextToken())));
		
		//끊겨 있는 노드는 입접리스트에서 삭제
		int min = 987654321;
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken()); 
			long b = Long.parseLong(st.nextToken());
			min = (int) Math.min(a, b);
			if(min!=1)
				list[min].clear();
			if(min==1){
				if(a==2 || b==2)
					list[min].remove(0);
				else if((a==n || b==n) && list[1].size()>1)
					list[min].remove(1);
				else if((a==n || b==n) && list[1].size()==1)
					list[min].remove(0);
			}
		}
		
		preUnion();
		//공사 구간이 1개 이하라면 모든 강의동은 연결되어 있음
		if(m>1)
			bw.write(kruskal());
		else
			bw.write("YES");
		br.close();
		bw.close();
		
	}
	
	//root 찾기
	public static int find(int node) {
		//자기 자신이 root인 경우 출력, 즉 뿌리만 자기 자신을 반환함
		if(parents[node] == node)
			return node;
		//자기 자신이 root가 아닌 경우, 자신의 부모의 부모를 호출... 재귀
		return parents[node] = find(parents[node]);
	}
	
	//노드들끼리 연결 시키기
	public static void preUnion() {
		
		if(list[1].size()>1) {
			parents[2]=1;
			parents[n]=1;
		}
		
		else if(list[1].size()==1) {
			if(list[1].get(0)==2)
				parents[2] = 1;
			else
				parents[n] = 1;
		}
		
		for(int i = 2; i<n; i++) {
			if(list[i].isEmpty())
				continue;
			parents[find(i+1)] = find(i);
		}
		
	}
	
	public static String kruskal() {
		long cost = 0;
		
		while(!pq.isEmpty()) {
			Node17490 now = pq.poll();
			int from = now.from;
			int w = now.w;
			
			if(find(from)==0)
				continue;
			parents[find(from)] = 0;
			cost+=(long) w;
			if(cost>k)
				return "NO";
		}
		for(int i = 1; i<=n; i++)
			if(parents[find(i)] != 0)
				return "NO";
		return "YES";
	}

}