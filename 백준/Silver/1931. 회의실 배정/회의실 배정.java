import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		int[][] arr = new int[t][2];
		for(int i = 0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
			//음수값이 리턴되면 o1, o2의 자리를 유지하며, 양수값이 리턴되면 o1,과 o2의 자리를 바꾼다.
			@Override
			public int compare(int[] o1, int[] o2) {
				//종료시간이 동일한 예약이 있다면, 시작시간으로 오름차순 정렬한다.
				//이는 시작시간과 종료시간이 동일한 예약이 포함되어 있을 것을 고려한 이중 정렬이다.
				if(o1[1]==o2[1])
					return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
			
		});
		
		int pre = 0;
		int cnt = 0;
		for(int i = 0; i<t; i++) {
			//예약하고자하는 회의의 시작시간이 이전 회의의 종료시간보다 이후인 경우 회의를 잡으며, cnt를 증가시킨다
			if(pre<=arr[i][0]) {
				pre=arr[i][1];
				cnt++;
			}
		}
		
		bw.write(String.valueOf(cnt));
		br.close();
		bw.close();
	}

}