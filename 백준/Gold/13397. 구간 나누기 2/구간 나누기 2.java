import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int n, m;
	public static int[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		
		int num;
		int l = 0; 
		int r = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			num = Integer.parseInt(st.nextToken());
			r = Math.max(r, num);
			arr[i] = num;
		}
		
		while(l<r) {
			int mid = l+r >>> 1;
			if(solution(mid)>m) {
				l=mid+1;
			}
			else if(solution(mid)<=m)
				r=mid;
		}
		bw.write(String.valueOf(r));
		br.close();
		bw.close();
	}
	
	public static int solution(int mid) {
		int min = 987654321;
		int max = 0;
		int cnt = 1;
		for(int i = 0; i<n; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
			if(max-min > mid) {
				cnt++;
				min = 987654321;
				max = 0;
				i--;
			}
		}
		return cnt;
	}

}