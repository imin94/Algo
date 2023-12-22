import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int R, C;
	public static boolean[][] flag;
	public static int[][] arr;
	public static int[][] having;
	public static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[R][C];

		// 배열 값 입력
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		int year = 0;
		while (true) {
			flag = new boolean[R][C];
			having = new int[R][C];
			// 빙하가 몇 덩어리인지 확인
			int cnt = 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (arr[r][c] == 0 || flag[r][c])
						continue;
					cnt++;
					bfs(r, c);
				}
			}
			for(int r = 0; r<R; r++) {
				for(int c = 0; c<C; c++) {
					arr[r][c] = Math.max(arr[r][c]-having[r][c],0);
				}
			}
			if (cnt >= 2) {
				ans = year;
				break;
			}
			if (cnt == 0) {
				ans = 0;
				break;
			}
			year++;
		}
		
		bw.write(String.valueOf(ans));
		br.close();
		bw.close();

	}

	public static void bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { sr, sc });

		while (!que.isEmpty()) {
			int[] now = que.poll();
			int r = now[0];
			int c = now[1];

			if (flag[r][c])
				continue;
			flag[r][c] = true;

			for (int i = 0; i < 4; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];

				// 범위를 벗어나거나, 0이거나, 방만한 경우 continue;
				if (dr < 0 || dr >= R || dc < 0 || dc >= C)
					continue;
				if (arr[dr][dc] == 0) {
					having[r][c]++;
					continue;
				}
				if (flag[dr][dc])
					continue;
				que.add(new int[] { dr, dc });
			}
		}
	}

}