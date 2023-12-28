import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static int[] arr;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		
		int left = 0;
		int right = 0;
		int num = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			
			//길이가 가장 긴 강의가 탐색의 최소값(left)이 된다 
			left = Math.max(left, num);
			//모든 강의의 길이를 합친 값이 탐색의 최대값(right)이 된다
			right += num;
		}
		
		while(left<=right) {
			int mid = (left+right) >>> 1;
			
			//m보다 블루레이의 개수가 큰 경우
			if(getCnt(mid)>m) {
				left = mid + 1;
			}
			//m보다 블루레이의 개수 작거나 같은 경우 
			else
				right = mid - 1;
		}
		
		bw.write(String.valueOf(left));
		br.close();
		bw.close();
		
	}
	
	//입력된 mid 값으로 블루레이의 크기를 제한했을 때 몇개의 블루레이가 필요한지 반환
	public static int getCnt(int mid) {
		int sum = 0;
		int cnt = 0;
		for(int i = 0; i<n; i++) {
			//블루레이의 크기를 벗어나면 cnt++, sum 0으로 재할당
			if(sum+arr[i]>mid) {
				sum = 0;
				cnt++;
			}
			sum += arr[i];
		}
		
		//sum에 값이 있는 경우 하나의 블루레이로 측정
		if(sum>0)
			cnt++;
		return cnt;
	}

}