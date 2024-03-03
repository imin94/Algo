import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[n];
		long max = 0;
		long num = 0;
		for(int i = 0; i<n; i++) {
			num = Long.parseLong(br.readLine());
			arr[i] = num;
			max = Math.max(max, num);
		}
		
		long sum = 0;
		long min = 0;
		long l = 0;
		long r = m*max;
		while(l<=r) {
			long mid = l+r >>> 1;
			sum = 0;
			for(int i = 0; i<n; i++) {
				sum+=mid/arr[i];
				if(sum>=m)
					break;
			}
			
			if(sum>=m)
				r = mid-1;
			else
				l = mid+1;
		}
		
		bw.write(String.valueOf(l));
		br.close();
		bw.close();
		
	}

}