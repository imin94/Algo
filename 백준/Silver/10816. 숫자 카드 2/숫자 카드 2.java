import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static int[] nums;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//가지고 있는 카드 n개의 값 입력
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		//n개의 카드를 오름차순으로 정렬(이분 탐색을 하기 위해서는 정렬이 선행적으로 필요)
		Arrays.sort(nums);
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<m; i++) {
			int num = Integer.parseInt(st.nextToken());
			//upperBound에서 lowerBound를 뺀 값이 해당 num를 몇개 가지고 있는지 나타냄
			sb.append(upperBound(num)-lowerBound(num)).append(" ");
		}
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	
	//target보다 큰 첫 번째 값의 인덱스를 출력
	public static int upperBound(int target) {
		int s = 0;
		int e = n;
		
		while(s<e) {
			int mid = (s+e)/2;
			if(nums[mid]<=target)
				s=mid+1;
			else
				e=mid;
		}
		return s;
	}
	//target과 같거나 큰 첫 번째 값의 인덱스를 출력
	public static int lowerBound(int target) {
		int s = 0;
		int e = n;
		
		while(s<e) {
			int mid = (s+e)/2;
			if(nums[mid]>=target)
				e=mid;
			else
				s=mid+1;
		}
		return s;
	}
}