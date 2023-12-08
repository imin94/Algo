import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Vertex1774 {
	
	double x;
	double y;
	
	public Vertex1774(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
}

class Node1774 implements Comparable<Node1774>{
	int from;
	int to;
	double w;
	
	public Node1774(int from, int to, double w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}

	@Override
	public int compareTo(Node1774 o) {
		return this.w<o.w ? -1:1;
	}
	
}

public class Main {
	
	public static int n, m;
	public static int mCnt;
	public static int[] parents;
	public static Queue<Node1774> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//우주신 좌표 arr에 입력
		Vertex1774[] arr = new Vertex1774[n+1];
		parents = new int[n+1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			parents[i] = i;
			arr[i] = new Vertex1774(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		
		//우주신들 끼리의 좌표를 비교해 우선순위 큐에 가까운 순으로 넣음
		for(int i = 1; i<=n; i++) {
			for(int j = i+1; j<=n; j++) {
				//x,y 모두 double Type으로 overflow 통과
				double w = Math.sqrt(Math.pow(arr[i].y-arr[j].y,2)+Math.pow(arr[i].x-arr[j].x,2));
				pq.add(new Node1774(i,j,w));
			}
		}
		
		//이미 연결된 간선을 체크, 양방향으로 정보가 들어올 수 있어 중복을 제외한 값을 출력해야함
		boolean[][] flag = new boolean[n+1][n+1];
		mCnt = 0;
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(find(from) != find(to))
				parents[find(from)] = find(to);
			
			if(flag[from][to])
				continue;
			flag[from][to] = true;
			flag[to][from] = true;
			mCnt++;
		}
		
		bw.write(String.format("%.2f", kruskal()));
		br.close();
		bw.close();
		
	}
	
	public static int find(int node) {
		if(parents[node] == node)
			return node;
		return parents[node] = find(parents[node]);
	}
	
	public static double kruskal() {
		double dis = 0;
		
		while(!pq.isEmpty()) {
			Node1774 now = pq.poll();
			int from = now.from;
			int to = now.to;
			
			if(find(from) != find(to)) {
				parents[find(from)] = find(to);
				dis += now.w;
			}
		}
		return dis;
	}
	
}
