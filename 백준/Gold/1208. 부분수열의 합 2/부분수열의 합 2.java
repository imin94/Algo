import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int[] nums;
	public static List<Integer> leftList = new ArrayList<>();
	public static List<Integer> rightList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		nums = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		int mid = (n) >>> 1;
		//n은 최대 40까지 있으며, 40개의 부분수열을 구하면 시간초과 되므로 반반을 나누어 20개의 부분 수열의 합을 각각 list에 저장
		getSumList(0,mid,0,leftList);
		getSumList(mid,n,0,rightList);
		
		//각각 리스트를 정렬
		Collections.sort(leftList);
		Collections.sort(rightList);
		
		int l = 0;
		int r = rightList.size()-1;
		
		long cnt = 0;
		while(true) {
			if(l==leftList.size() || r<0)
				break;
			int costL = leftList.get(l);
			int costR = rightList.get(r);
			
			//투 포인트의 합이 원하는 수가 나왔으면, 동일한 수가 몇개 있는지 카운트 해(정렬로 동일한 수가 연달아 나오기 때문에 가능) n*m을 cnt에 더해 준다 
			if(costL+costR == s) {
				
				long cntL = 0;
				while(l<leftList.size() && leftList.get(l)==costL) {
					l++;
					cntL++;
				}
				
				long cntR = 0;
				while(r>=0 && rightList.get(r)==costR) {
					r--;
					cntR++;
				}
				
				cnt += cntL*cntR;
			}
			else if(costL+costR<s)
				l++;
			else
				r--;
		}
		//각 부분 수열의 합에 0이 들어가 있기에, 만약 합이 0인 부분 수열을 찾는 경우 1을 뺴줌
		if(s==0)
			cnt--;
		bw.write(String.valueOf(cnt));
		br.close();
		bw.close();
		
	}
	
	public static void getSumList(int srt, int end, int sum, List<Integer> list) {
		if(srt==end) {
			list.add(sum);
			return;
		}
		getSumList(srt+1,end,sum,list);
		getSumList(srt+1,end,sum+nums[srt],list);
	}

}