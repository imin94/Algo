import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static int[] nums; 
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		dfs(N,M,0);
		bw.write(sb.toString());
		br.close();
		bw.close();
		
	}
	
	public static void dfs(int n, int m, int depth){
		if(depth==m) {
			for(int i = 0; i<m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		for(int i = 0; i<n; i++) {
			arr[depth]=i+1;
			dfs(n,m,depth+1);
		}
	}

}