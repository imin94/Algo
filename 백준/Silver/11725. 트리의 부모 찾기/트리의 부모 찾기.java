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
		
		int n = Integer.parseInt(br.readLine());
		List<Integer>[] tree = new ArrayList[n+1];
		
		for	(int i = 0; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		boolean[] flag = new boolean[n+1];
		int[] ans = new int[n+1];
		
		Queue<Integer> que = new LinkedList<>();
		que.add(1);
		flag[1]=true;
		while(!que.isEmpty()) {
			int idx = que.poll();
			for(int i : tree[idx]) {
				if(!flag[i]) {
					flag[i]=true;
					que.add(i);
					ans[i] = idx;
				}
			}
		}
		
		for(int i = 2; i<=n; i++) {
			sb.append(ans[i]).append('\n');
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();

	}

}