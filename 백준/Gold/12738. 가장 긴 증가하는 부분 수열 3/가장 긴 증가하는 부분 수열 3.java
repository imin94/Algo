import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] nums = new int[n];
		int[] lis = new int[n];
		
		//순열에 값 입력
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		//lis의 첫 번째 인자는 순열의 첫 번째 값으로 할당
		lis[0]=nums[0];
		int len = 1;
		
		//순얄의 2번째 인자부터 lis에 해당하는지 이분 탐색(lower bound)
		for(int i = 1; i<n; i++) {
			int target = nums[i];
			
			if(target>lis[len-1]) {
				lis[len]=target;
				len++;
			}
			else {
				int s = 0;
				int e = len-1;
				
				while(s<e) {
					int mid = (s+e)>>>1;
					//lis의 중앙 값이 target보다 같거나  크다면 start부터 mid까지 재탐색
					if(target<=lis[mid])
						e = mid;
					//lis의 중앙 값이 target보다 작다면 mid+1부터 end까지 재탐색
					else
						s = mid + 1;
				}
				lis[s] = target;
			}
		}
		
		bw.write(String.valueOf(len));
		br.close();
		bw.close();
	}

}
