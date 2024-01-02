import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node1167{
	int to,w;

	public Node1167(int to, int w) {
		this.to = to;
		this.w = w;
	}
	
}

public class Main {
	
	public static int max=0, maxIdx=0;
	public static int[] flag;
	public static List<Node1167>[] list;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		for(int i = 1; i<=n; i++)
			list[i] = new ArrayList<>();
		
		//인접리스트 생성
		for(int i = 1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			while(true) {
				int to = Integer.parseInt(st.nextToken());
				if(to==-1)
					break;
				int w = Integer.parseInt(st.nextToken());
				list[from].add(new Node1167(to,w));
			}
		}
		
		//임의의 노드(1)에서 가장 먼 노드로 이동
		flag = new int[n+1];
		dfs(1,1,0);
		
		flag = new int[n+1];
		max = 0;
		dfs(maxIdx,maxIdx,0);
		
		bw.write(String.valueOf(max));
		br.close();
		bw.close();
	}
	
	public static void dfs(int from, int now, int cost) {
		//누적된 간선의 길이가 더 긴 경웅 max값 갱신, 도착 노드 갱신
		if(max<cost) {
			max = cost;
			maxIdx = now;
		}
		
		for(Node1167 next : list[now]) {
			int to = next.to;
			if(to==from || flag[to]>cost+next.w)
				continue;
			flag[to] = cost+next.w;
			dfs(now,to,cost+next.w);
		}
	}

}