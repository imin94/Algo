import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static int s, e, min;
	public static int[] from = new int[100001];
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		min = 987654321;
		//시작점이 끝점보다 큰 경우(감소 밖에 없는 경우, cnt를 1씩 늘리면서 지나가는 노드를 출력)
		if(s>=e) {
			min = back(s,e,0);
			bw.write(String.valueOf(min)+"\n");
			bw.write(sb.toString());
		}
		else {
			bfs();
			bw.write(String.valueOf(min)+"\n");
			Stack<Integer> stack = new Stack<>();
			while(true) {
				stack.add(e);
				if(from[e]==s)
					break;
				e = from[e];
			}
			stack.add(s);
			while(!stack.isEmpty())
				sb.append(stack.pop()).append(" ");
			
			bw.write(sb.toString());
		}
		br.close();
		bw.close();
	}

	public static int back(int start, int end, int cnt) {
		for (int i = start; i >= end; i--) {
			sb.append(i).append(" ");
			if(i!=start)
				cnt++;
		}
		return cnt;
	}
	//-1, 1, *2가 가능한 범위이면 진행하며 que에 저장하며 동시에 행적을 남김
	public static void bfs(){
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {s,0});
		
		//-1인경우 방문하지 않음
		for(int i = 0; i<=100000; i++)
			from[i] = -1;
		from[s] = s;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int idx = now[0];
			int cnt = now[1];
			
			if(idx == e) {
				min = cnt;
				break;
			}
			
			//이동할 수 있고, 이전에 방문한 적이 없으면 이동하며 que에 넣음
			//-1칸 걷기
			if(idx-1>=0 && from[idx-1]==-1) {
				que.add(new int[] {idx-1,cnt+1});
				from[idx-1] = idx;
			}
			//+1칸 걷기
			if(idx+1<=100000 && from[idx+1]==-1) {
				que.add(new int[] {idx+1,cnt+1});
				from[idx+1] = idx;
			}
			//순간이동
			if(idx*2<=100000 && from[idx*2]==-1) {
				que.add(new int[] {idx*2,cnt+1});
				from[idx*2] = idx;
			}
			
		}
		
	}
}