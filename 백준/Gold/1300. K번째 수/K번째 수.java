import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		//가장 작은 수
		int s = 1;
		//가장 큰 수
		int e = k;
		
		while(s<e) {
			int mid = (s+e) >>> 1;
			long cnt = 0;
			for(int i=1; i<=n; i++) {
				cnt+=Math.min(mid/i,n);
			}
			
			if(cnt>=k)
				e=mid;
			else
				s=mid+1;
		}
		
		bw.write(String.valueOf(s));
		br.close();
		bw.close();
	}

}