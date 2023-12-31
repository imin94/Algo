import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		
		//A의 누적합
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] A = new int[n+1];
		for(int i = 1; i<=n; i++) {
			A[i] = A[i-1] + Integer.parseInt(st.nextToken());
		}
		
		//B의 누적합
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] B = new int[m+1];
		for(int i = 1; i<=m; i++) {
			B[i] = B[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int sizeA = n*(n+1)/2;
		long[] partA = new long[sizeA];
		int sizeB = m*(m+1)/2;
		long[] partB = new long[sizeB];
		
		//A의 부 배열의 합 입력
		int idx = 0;
		for(int i = 0; i<n; i++) {
			for(int j = 1; j+i<=n; j++) {
				partA[idx++]=A[j+i]-A[i];
			}
		}
		//B의 부 배열의 합 입력
		idx = 0;
		for(int i = 0; i<m; i++) {
			for(int j = 1; j+i<=m; j++) {
				partB[idx++]=B[j+i]-B[i];
			}
		}
		
		//A, B의 부 배열의 합을 오름차순으로 정렬
		Arrays.sort(partA);
		Arrays.sort(partB);
		
		//투포인터로 두 부 배열의 최소값과 최대값을 더하는 방식으로 접근
		long cnt = 0;
		int l = 0;
		int r = sizeB-1;
		while(l<sizeA && 0<=r) {
			long sumA = partA[l];
			long sumB = partB[r];
			long sum = sumA + sumB;
			//sum이 더 작다면 l++해 sum을 키움
			if(sum<t)
				l++;
			//sum이 더 크다면 r--해 sum을 줄임
			else if(sum>t)
				r--;
			//sum이 같다면 같은 수를 A와 B 각각 구해 곱해준 값을 cnt에 더함
			else {
				long cntA = 0;
				long cntB = 0;
				while(l<sizeA && sumA == partA[l]) {
					cntA++;
					l++;
				}
				while(0<=r && sumB == partB[r]) {
					cntB++;
					r--;
				}
				cnt += cntA*cntB;
			}
		}
		
		bw.write(String.valueOf(cnt));
		br.close();
		bw.close();

	}

}