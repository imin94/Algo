import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		Deque<int[]> deq = new ArrayDeque<>();

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int[] num = {Integer.parseInt(st.nextToken()), i};
			while (!deq.isEmpty() && deq.peekFirst()[1] <= i - L) {
				deq.pollFirst();
			}
			if(deq.isEmpty())
				deq.add(num);
			else if (deq.peekFirst()[0] >= num[0]) {
				deq.pollFirst();
				deq.addFirst(num);
			}
			else if (deq.peekFirst()[0] < num[0]) {
				while(deq.peekLast()[0]>=num[0]) {
					deq.pollLast();
				}
				deq.addLast(num);
			}
			sb.append(deq.peekFirst()[0]).append(" ");
		}
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}