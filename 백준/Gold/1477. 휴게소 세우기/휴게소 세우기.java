import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static int N,M,L;
	public static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		if(N != 0)
			st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++)
			list.add(Integer.parseInt(st.nextToken()));
		
		list.add(0);
		list.add(L);
		Collections.sort(list);
		
		int l = 1;
		int r = L;
		while(l<=r) {
			int mid = (l+r) >>> 1;
			if(solution(mid)>M)
				l = mid+1;
			else
				r = mid-1;
		}
		
		bw.write(String.valueOf(l));
		br.close();
		bw.close();
	}
	
	public static int solution(int mid) {
		int cnt = 0;
		for(int i = 0; i<list.size()-1; i++)
			cnt += (list.get(i+1)-list.get(i)-1)/mid;
		return cnt;
	}

}