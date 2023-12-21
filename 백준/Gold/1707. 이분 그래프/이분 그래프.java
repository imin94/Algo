import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0; t<tc; t++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			//이분 그래프 탐색을 위해 각 노드별 색상(1 or -1)을 표시할 배열 생성
			int[] flag = new int[n+1];
			
			//인접리스트 생성
			List<Integer>[] list = new ArrayList[n+1];
			for(int i = 1; i<=n; i++)
				list[i] = new ArrayList<>();
			for(int i = 0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list[a].add(b);
				list[b].add(a);
			}
			
			int result = 0;
			loop:
			//모든 노드에 대해 연결된 노드들을 탐색 시작
			for(int i = 1; i<=n; i++) {
				//i번째 노드의 색상을 칠하지 않은 경우 연결된 모든 노드에 대해 탐색 
				if(flag[i]==0) {
					Queue<Integer> que = new LinkedList<>();
					que.add(i);
					flag[i] = 1;
					
					while(!que.isEmpty()) {
						int now = que.poll();
						for(int j : list[now]) {
							//연결된 노드와 색상이 같은 경우 순환이 이루어지지 않아 이분 그래프가 아님
							if(flag[now]==flag[j]) {
								result = 1;
								break loop;
							}
							
							//색이 안질해진 경우 반대색을 칠함
							if(flag[j]==0) {
								flag[j] = flag[now]*-1;
								que.add(j);
							}
						}
					}
				}
			}
			if(result == 1)
				sb.append("NO").append("\n");
			else
				sb.append("YES").append("\n");
		}
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}

}