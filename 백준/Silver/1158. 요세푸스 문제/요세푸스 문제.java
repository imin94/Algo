import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Queue<Integer> que = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i<=n; i++) {
			que.offer(i);
		}
		sb.append('<');
		while(que.size() != 1) {
			for(int i = 0; i<k-1; i++) {
				que.offer(que.poll());
			}
			sb.append(que.poll()+", ");
		}
		sb.append(que.poll()+">");
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}