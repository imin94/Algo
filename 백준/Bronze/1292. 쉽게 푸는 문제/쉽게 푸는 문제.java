import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int cnt = 1;
		int loop = 1;
		int sum = 0;
		loop:
		while(true) {
			for(int i=1; i<=loop; i++) {
				if(cnt>=s)
					sum+=loop;
				if(++cnt>e)
					break loop;
			}
			loop++;
		}
		System.out.println(sum);
	}
}