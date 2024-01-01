import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node1967 implements Comparable<Node1967> {

	int to, w;

	public Node1967(int to, int w) {
		this.to = to;
		this.w = w;
	}

	@Override
	public int compareTo(Node1967 o) {
		return w - o.w;
	}

}

public class Main {

	public static int max = 0;
	public static int maxIdx = 0;
	public static boolean[] flag;
	public static List<Node1967>[] list;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		if (n == 1)
			bw.write("0");
		else {
			// 인접리스트 초기화
			list = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++)
				list[i] = new ArrayList<>();

			// 인접리스트 입력
			for (int i = 1; i < n; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				list[a].add(new Node1967(b, w));
				list[b].add(new Node1967(a, w));
			}

			// 임의의 점에서 가장 길이가 긴 노드까지 이동
			flag = new boolean[n + 1];
			flag[1] = true;
			dfs(1, 0);

			// 이동한 노드부터 다시 길이가 가장 긴 노드까지 이동해 트리의 지름을 구함
			flag = new boolean[n + 1];
			flag[maxIdx] = true;
			dfs(maxIdx, 0);

			bw.write(String.valueOf(max));
		}
		br.close();
		bw.close();
	}

	public static void dfs(int s, int cost) {
		if (max < cost) {
			max = cost;
			maxIdx = s;
		}

		for (Node1967 next : list[s]) {
			int to = next.to;
			int w = next.w;

			if (flag[to])
				continue;
			flag[to] = true;
			dfs(to, cost + w);
		}
	}

}