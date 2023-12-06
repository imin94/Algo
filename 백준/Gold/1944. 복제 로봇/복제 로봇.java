import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//시작점과 열쇠가 있는 좌표와 count을 나타낼 노드 Point 생성
class Point1944 {
	int r, c, count;

	public Point1944(int r, int c, int count) {
		this.r = r;
		this.c = c;
		this.count = count;
	}

}

//최소 스패닝트리 노드 시작점, 끝점, 비용
class Node1944 implements Comparable<Node1944>{
	int from, to, cost;

	public Node1944(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node1944 o) {
		return this.cost-o.cost;
	}
	
}

public class Main {

	public static List<Point1944> list;
	public static int n, m;
	public static char[][] arr;
	public static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static int[] parent;
	public static Queue<Node1944> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new char[n][n];
		list = new ArrayList<>();

		String str = "";
		for (int r = 0; r < n; r++) {
			str = br.readLine();
			for (int c = 0; c < n; c++) {
				char chr = str.charAt(c);
				arr[r][c] = chr;
				if(chr == 'S' || chr == 'K')
					list.add(new Point1944(r,c,0));
			}
		}
		
		for(int i = 0; i<=m; i++) {
			bfs(i);
		}
		bw.write(String.valueOf(kruskal()));
		br.close();
		bw.close();
		
	}
	
	//node의 조상 노드를 출력해줌
	public static int find(int node) {
		//부모노드를 계속 타고 올라가 가장 조상의 노드를 출력함
		if(parent[node] == node)
			return node;
		return parent[node] = find(parent[node]);
	}
	
	//조상 노드를 통일시켜줌(이어진 그래프를 나타냄)
	public static void union(int node1, int node2) {
		parent[node1]=node2;
	}
	
	public static int kruskal() {
		parent = new int[m+1];
		for(int i = 0; i<=m; i++) {
			parent[i] = i;
		}
		
		int cost = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Node1944 now = pq.poll();
			
			int node1 = find(now.from);
			int node2 = find(now.to);
			
			if(node1 != node2) {
				union(node1,node2);
				cost += now.cost;
				cnt++;
			}
		}
		if(cnt != m)
			return -1;
		return cost;
	}
	
	public static void bfs(int num) {
		Queue<Point1944> que = new LinkedList<>();
		boolean[][] flag = new boolean[n][n];
		//임의의 노드 시작점
		Point1944 start = list.get(num);
		que.add(start);
		flag[start.r][start.c] = true;
		
		while(!que.isEmpty()) {
			Point1944 now = que.poll();
			int r = now.r;
			int c = now.c;
			
			for(int i = 0; i<4; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];
				
				if(dr<0 || dr>=n || dc<0 || dc>=n || arr[dr][dc] == '1' || flag[dr][dc])
					continue;
				flag[dr][dc] = true;
				
				if(arr[dr][dc] == 'S' || arr[dr][dc] == 'K') {
					for(int j = 0; j< m+1; j++) {
						//임의의 노드 시작점으로 부터 가까운 순으로 넣음
						if(list.get(j).r == dr && list.get(j).c == dc) {
							pq.add(new Node1944(num,j,now.count+1));
						}
					}
				}
				que.add(new Point1944(dr,dc,now.count+1));
			}
		}
	}

}
