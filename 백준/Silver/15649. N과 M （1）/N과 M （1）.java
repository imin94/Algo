import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] idx;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		idx = new boolean[N];
		dfs(N,M,0);
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	
	public static void dfs(int n , int m, int dept) {
		if(dept==m) {
			for(int i=0; i<arr.length; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		for(int i = 0; i<n; i++) {
			if(idx[i]==false) {
				idx[i]=true;
				arr[dept]=i+1;
				dfs(n,m,dept+1);
				idx[i]=false;
			}
		}
	}
	
}