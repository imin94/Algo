import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static int[] arr, tree;
	static int n;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		int k = (int) Math.ceil(Math.log(n)/Math.log(2)) + 1 ;
		int size = (int) Math.pow(2,k);
		
		arr = new int[n + 1];
		tree = new int[size];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		init(1, n, 1);

		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				// 홀 > 짝
				if (arr[b] % 2 == 1 && c % 2 == 0) {
					update(1, n, 1, b, 1);
				}
				// 짝 > 홀
				else if (arr[b] % 2 == 0 && c % 2 == 1) {
					update(1, n, 1, b, 0);
				}
				arr[b] = c;
			} else if (a == 2 || a == 3) {
				long x = query(1, n, 1, b, c);
				if (a == 2) {
					sb.append(x + "\n");
				} else {
					sb.append((c - b + 1 - x) + "\n");
				}
			}
		}
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	static int init(int s, int e, int node) {
		if (s == e) {
			if (arr[s] % 2 == 0)
				return tree[node] = 1;
			else
				return 0;
		}
		int mid = (s + e) >>> 1;
		return tree[node] = init(s, mid, node * 2) + init(mid + 1, e, node * 2 + 1);
	}

	static int update(int s, int e, int node, int idx, int val) {
		if (idx < s || e < idx)
			return tree[node];
		if (s == e) {
			return tree[node] = val;
		}

		int mid = (s + e) >>> 1;
		return tree[node] = update(s, mid, node * 2, idx, val) + update(mid + 1, e, node * 2 + 1, idx, val);
	}

	static int query(int s, int e, int node, int L, int R) {
		if (R < s || e < L)
			return 0;
		if (L <= s && e <= R) {
			return tree[node];
		}
		int mid = (s + e) >>> 1;
		return query(s, mid, node * 2, L, R) + query(mid + 1, e, node * 2 + 1, L, R);
	}

}