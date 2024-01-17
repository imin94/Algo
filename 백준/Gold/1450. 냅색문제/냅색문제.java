import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int c;
	public static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++)
			list.add(Integer.parseInt(st.nextToken()));
		//최대 30개의 숫자를 완전탐색을 할 경우 시간초과, 반 씩 나누어 완탐을 하기 위해 left와 right로 나눔
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		//왼쪽 절반, 오른쪽 절반의 부분조합을 구해 c보다 작은 조합은 각각의 list에 담음(아무 것도 안담은 0도 포함되어 있음)
		dfs(left, 0, n/2, 0);
		dfs(right, n/2, n, 0);
		//오른쪽만 정렬해줌
		Collections.sort(right);
		
		int cnt = 0;
		int idx = 0;
		int len = right.size()-1;
		//왼쪽에 있는 값을 하나씩 꺼내 정렬된 오른쪽 조합에서 합이 c보다 작거나 같은 경우를 이분탐색을 통해 개수를 구함
		for(int i = 0; i<left.size(); i++) {
			idx = upperbound(0, len, left.get(i), right);
			cnt+=idx+1;
		}
		
		bw.write(String.valueOf(cnt));
		br.close();
		bw.close();
	}
	
	public static void dfs(List<Integer> returnList, int s, int e, int sum) {
		if(sum>c)
			return;
		
		if(s==e) {
			returnList.add(sum);
			return;
		}
		
		dfs(returnList,s+1,e,sum);
		dfs(returnList,s+1,e,sum+list.get(s));
	}
	
	public static int upperbound(int s, int e, int value, List<Integer> right) {
		while(s<=e) {
			int mid = (s+e) >>> 1;
			if(right.get(mid)<=c-value) {
				s=mid+1;
			}
			else
				e=mid-1;
		}
		return e;
	}

}