import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] sum = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			int num = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + num;
		}
		
		int m = Integer.parseInt(br.readLine());
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(sum[b]-sum[a-1]).append("\n");
		}
		
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}