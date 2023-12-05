import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] map = new int[100001];
	public static int[] find = {-1,1,2};

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<=100000; i++) {
			map[i] = 987654321;
		}
		map[a] = 0;
		dijk(a,b);
		bw.write(String.valueOf(map[b]));
		br.close();
		bw.close();
	}
	
	public static void dijk(int s, int e) {
		Queue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1]-b[1]));
		pq.add(new int[] {s,0});
		
		int to = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int from = now[0];
			int w = now[1];
			if(from==e)
				break;
			
			for(int i = 0; i<3; i++) {
				if(i<2) {
					to = from+find[i];
					//범위를 벗어나는 경우 해당 탐색 종료
					if(to<0 || to>100000)
						continue;
					//해당 좌표에 기재되어있는 가중치(이동횟수)가 현재 가중치 이하라면 해당 탐색 종료 
					if(map[to]<=w+1)
						continue;
					map[to] = w+1;
					pq.add(new int[] {to,w+1});
				}
				else {
					to = from*find[2];
					//범위를 벗어나는 경우 해당 탐색 종료
					if(to<0 || to>100000)
						continue;
					//해당 좌표에 기재되어있는 가중치(이동횟수)가 현재 가중치 이하라면 해당 탐색 종료 
					if(map[to]<=w)
						continue;
					map[to] = w;
					pq.add(new int[] {to,w});
				}
			}
		}
	}

}