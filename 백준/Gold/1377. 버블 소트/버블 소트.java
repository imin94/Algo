import java.io.*;

public class Main {

		public static void main(String[] args) throws IOException {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			//배열 생성
			int t = Integer.parseInt(br.readLine());
			int[] list = new int[t];
			int[] cnt = new int[1000001];
			
			//배열에 값 입력
			for(int i = 0; i<t; i++) {
				list[i] = Integer.parseInt(br.readLine());		
			}
			
			//숫자별 몇번 나왔는지 count
			for(int i = 0; i<t; i++) {
				cnt[list[i]]++;
			}
			
			//몇 번째로 큰 수인지 누적합
			int sumCnt = 0;
			for(int i = 0; i<1000001; i++) {
				sumCnt+=cnt[i];
				cnt[i]=sumCnt;
			}
			
			//입력된 idx 위치와 몇 번째로 큰 수인지 비교하여 최대 이동 횟수 구하기
			int max = 0;
			for(int i = 1; i<=t; i++) {
				max = Math.max(max, i-cnt[list[i-1]]);
			}
			
			//Script상 변화가 없어야 종료이므로 버블 정렬 이후 +1
			System.out.print(max+1);
		}

	}