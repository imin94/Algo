import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int M;
	public static StringBuilder sb = new StringBuilder();
	public static List<Integer> list = new ArrayList<Integer>();
	public static boolean[] flag;
	public static int[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		flag = new boolean[N];
		arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		
		dfs(0);
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	
	public static void dfs(int depth) {
		if(depth == M) {
			for(int i = 0; i<M; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append('\n');
			
			return;
		}
		int last = 0;
		for(int i = 0; i<N; i++) {
			int value = list.get(i);
			if(flag[i] || last==value)
				continue;
			flag[i]=true;
			last = value;
			arr[depth]=list.get(i);
			dfs(depth+1);
			flag[i]=false;
		}
	}

}