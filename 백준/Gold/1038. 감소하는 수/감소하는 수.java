import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	public static int n;
	public static List<Long> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		//n이 10이하인 경우, n자신이 n번째 감소하는 수 이므로 자기자신 출력 
		if(n<=10)
			bw.write(String.valueOf(n));
		//0부터 9까지 하나씩 넣으면서 자기 자신*10 + 자기 자신보다 낮은 수를 넣어줄 반복문 & 재귀 실행
		else {
			for(int i = 0; i<=9; i++)
				dfs(i,1);
			Collections.sort(list);
			if(list.size()<=n)
				bw.write("-1");
			else
				bw.write(String.valueOf(list.get(n)));
		}
		br.close();
		bw.close();		
		
	}
	
	public static void dfs(long now, int idx) {
		//감소하는 수 최대 값은 9876543210 10자리로 10자리를 넘는 경우 return
		if(idx>10)
			return;
		
		//감소하는 수를 list에 넣어줌
		list.add(now);
		//마지막 수보다 작은 수를 뒤에 붙여서 감소하는 수에 넣어줌
		for(int i = 0; i<now%10; i++) {
			//자기자신은 10 곱하고 자기보다 낮은 수를 다음 수로 넣음
			dfs(now*10+i,idx+1);
		}
		
	}

}