import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		int[] sum = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		//누적합 배열 입력
		for(int i = 1; i<=n; i++) {
			sum[i] = sum[i-1]+Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		int cnt = 0;
		for(int i = x; i<=n; i++) {
			//x일동안 누적 방문횟수
			int value = sum[i]-sum[i-x];
			//max값이 갱신되면 cnt 초기화
			if(max<value) {
				cnt = 1;
				max = value;
			}
			//max값과 동일한 값이 나올경우 cnt++
			else if(max==value)
				cnt++;
		}
		//최대 방문이 0인경우 SAD
		if(max==0)
			bw.write("SAD");
		else
			bw.write(String.valueOf(max)+"\n"+String.valueOf(cnt));
		br.close();
		bw.close();
	}

}