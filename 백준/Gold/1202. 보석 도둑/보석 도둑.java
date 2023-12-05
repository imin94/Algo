import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] J = new int[n][2];
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			J[i][0] = Integer.parseInt(st.nextToken());;
			J[i][1] = Integer.parseInt(st.nextToken());;
		}
		//입력 받은 보석을 무게(오름차순), 가격(내림차순)으로 정렬한다 (람다 사용)
		Arrays.sort(J, (a,b) -> a[0] == b[0] ? b[1]-a[1] : a[0]-b[0]);
		//입력 받은 보석을 무게(오름차순), 가격(내림차순)으로 정렬한다 (comparator 사용)
//		Arrays.sort(J, new Comparator<int[]>() {
//			public int compare(int[] o1, int[] o2) {
//				if(o1[0]==o2[0])
//					return o2[1]-o1[1];
//				return o1[0]-o2[0];
//			}
//		});
		
		int[] back = new int[k];
		for(int i = 0; i<k; i++) {
			back[i] = Integer.parseInt(br.readLine());
		}
		//가방 무게를 오름차순으로 정렬
		Arrays.sort(back);
		//가방 무게를 하나씩 올려가며 가방에 넣을 수 있는 보석들을 한눈에 보기 위해 우선순위 큐를 구현
		Queue<int[]> pq = new PriorityQueue<>((a,b) -> b[1]-a[1]);
		int jIdx = 0;
		long sum = 0;
		for(int i=0; i<back.length; i++) {
			int w = back[i];
			
			//i번째 가방의 무게 이하인 보석들을 pq에 넣음 
			while(jIdx<n && J[jIdx][0]<=w) {
				pq.add(J[jIdx++]);
			}
			
			//가방은 남았으나 보석이 없는 경우 || 가방 무게에 해당하는 보석이 없는 경우
			if(pq.isEmpty())
				continue;
			
			sum+=(long)pq.poll()[1];
		}
		bw.write(String.valueOf(sum));
		br.close();
		bw.close();
		
	}

}