import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int n, L, R;
	public static int[][] arr;
	public static boolean[][] flag;
	public static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[n][n];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		int cnt = 0;
		while (true) {
			cnt = 0;
			flag = new boolean[n][n];

			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (flag[r][c])
						continue;
					bfs(r, c);
					cnt++;
				}
			}

			if (cnt == n * n)
				break;

			ans++;
		}

		bw.write(String.valueOf(ans));
		br.close();
		bw.close();
	}

	public static void bfs(int sr, int sc) {
		int sum = 0;
		int cnt = 0;
		int mean = 0;
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { sr, sc });
		Queue<int[]> que2 = new LinkedList<>();

		while (!que.isEmpty()) {
			int[] now = que.poll();
			int r = now[0];
			int c = now[1];

			if (flag[r][c])
				continue;
			flag[r][c] = true;
			que2.add(new int[] { r, c });
			
			sum += arr[r][c];
			cnt++;

			for (int i = 0; i < 4; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];

				if (dr < 0 || dr >= n || dc < 0 || dc >= n || flag[dr][dc])
					continue;
				int value = Math.abs(arr[dr][dc] - arr[r][c]);
				if (value < L || value > R)
					continue;

				que.add(new int[] { dr, dc });
			}
		}

		if (cnt != 1) {
			mean = sum / cnt;
			while (!que2.isEmpty()) {
				int[] now = que2.poll();
				int r = now[0];
				int c = now[1];

				arr[r][c] = mean;
			}
		}
	}
}