import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] parents;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1];
		for(int i = 1; i<=n; i++)
			parents[i] = i;
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int B = find(b);
			int C = find(c);
			if(a==0) {
				if(B==C)
					continue;
				else if(B<C)
					parents[C] = B;
				else
					parents[B] = C;
			}
			else {
				if(B==C)
					bw.write("YES"+"\n");
				else
					bw.write("NO"+"\n");
			}
		}
		br.close();
		bw.close();
	}
	
	public static int find(int node) {
		if(parents[node] == node)
			return node;
		return parents[node] = find(parents[node]);
	}

}