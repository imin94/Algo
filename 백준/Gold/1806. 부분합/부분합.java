import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		int[] sum = new int[n+1];
		//누적합 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
		}
		
		//모든 합을 누적해도 s보다 작다면 0 출력
		if(sum[n]<s)
			bw.write("0");
		else {
			//1번 인덱스부터 탐색
			int l = 0;
			int r = 1;
			int min = 987654321;
			while(true) {
				//누적합이 s보다 크면 시작점을 다음점으로 이동
				int value = sum[r] - sum[l];
				if(value>=s) {
					min = Math.min(min, r-l);
					if(min == 1)
						break;
					l++;
				}
				//누적합이 s보다 작으면 범위를 늘려 재탐색
				else
					r++;
				if(r>n)
					break;
			}
			bw.write(String.valueOf(min));
		}
		br.close();
		bw.close();
	}

}