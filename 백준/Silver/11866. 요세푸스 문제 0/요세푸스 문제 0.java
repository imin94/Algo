import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Queue<Integer> que = new LinkedList<>();
		
		for(int i = 1; i<=n; i++)
			que.add(i);
		
		bw.write("<");
		int cnt = 0;
		while(!que.isEmpty()) {
			int num = que.poll();
			if(++cnt==k) {
				cnt=0;
				if(que.size()==0)
					bw.write(num+">");
				else
					bw.write(num+", ");
			}
			else
				que.add(num);
		}
		
		br.close();
		bw.close();
	}

}