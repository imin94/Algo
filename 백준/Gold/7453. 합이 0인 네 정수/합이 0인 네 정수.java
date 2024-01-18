import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] left, right;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			A[i] = a;
			B[i] = b;
			C[i] = c;
			D[i] = d;
		}
		
		left = new int[n*n];
		right = new int[n*n];
		//4개의 배열을 왼쪽 2개, 오른쪽 2개를 더해 2개의 배열(left, right)로 만들어 준다
		//이때 배열의 크기는 최대 4000*4000 = 16000000
		int idx = 0;
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				left[idx] = A[i]+C[j];
				right[idx] = B[i]+D[j];
				idx++;
			}
		}
		
		//각 배열을 오름차순 정렬
		Arrays.sort(left);
		Arrays.sort(right);
		
		//투포인터로 왼쪽 리스트의 가장 작은 값과 오른쪽 리스트의 가장 큰 값의 합을 기준으로 탐색
		int s = 0;
		int size = n*n;
		int e = size-1;
		long cnt = 0;
		while(s<size && e>=0) {
			int sValue = left[s];
			int eValue = right[e];
			
			//합이 0인 경우 같은 수의 개수만큼 카운트 한 후 곱한 값을 더함
			if(sValue + eValue == 0) {
				long sCnt = 1; 
				long eCnt = 1;
				
				while(s+1<size && left[s+1]==sValue) {
					s++;
					sCnt++;
				}
				while(e>0 && right[e-1]==eValue) {
					e--;
					eCnt++;
				}
				
				cnt+=sCnt*eCnt;
				s++;
			}
			else if(sValue+eValue>0)
				e--;
			else
				s++;
		}
		
		bw.write(String.valueOf(cnt));
		br.close();
		bw.close();
		
	}
	
}