import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static List<int[]>[] list;
	public static int n, start, mid, end;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i = 1; i<=n; i++)
			list[i] = new ArrayList<>();
		//단방향 인접리스트 생성
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[from].add(new int[] {to,w});
		}
		//시작점 s, 중간지점 mid, 도착점 e
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int mid = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		//시작점에서 중간지점 까지의 거리 v1, 중간지점에서 도착점까지의 거리 v2, v1을 구하는 도중에 도착지점이 있어도 괜찮음
		int v1 = dijkstra(s,mid,0);
		int v2 = dijkstra(mid,e,0);
		//두 경로 중 하나라도 갈 수 없다면 -1 출력
		if(v1==-1 || v2==-1)
			bw.write("-1");
		else
			bw.write(String.valueOf(v1+v2));
		//중간 여백
		bw.write(" ");
		//시작점에서 중간 지점을 안거치고 도착점 까지의 최단 경로 추출
		int v3 = dijkstra(s,e,mid);
		bw.write(String.valueOf(v3));
		br.close();
		bw.close();
		
	}
	
	public static int dijkstra(int s, int e, int excep) {
		int[] cost = new int[n+1];
		for(int i = 1; i<=n; i++)
			cost[i] = Integer.MAX_VALUE;
		
		//누적된 cost를 기준(오름차순)으로 우선순위 큐 구현 
		Queue<int[]> pq = new PriorityQueue<>((o1,o2)->(o1[1]-o2[1]));
		//출발점은 가중치 없음
		pq.add(new int[] {s,0});
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int from = now[0];
			//이미 방문한 누적값이 도착지의 누적값보다 크다면 
			if(cost[from]<now[1])
				continue;
			//만약 목적지에 도착한 경우 누적된 cost 반환
			if(from == e)
				return now[1];
			//피해야하는 중점을 방문한 경우 해당 값은 쓰지 않음
			if(from == excep)
				continue;
			//연결된 모든 경로를 탐색
			for(int[] next : list[from]) {
				int to = next[0];
				int w = next[1];
				//이미 방문한 누적값이 자신의 누적값보다 크다면 
				if(cost[to]<cost[from]+w)
					continue;
				cost[to] = now[1]+w;
				pq.add(new int[] {to,cost[to]});
			}
		}
		//만약 목적지에 도달하지 못했다면 -1 출력
		return -1;
	}

}