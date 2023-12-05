import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int cnt = 0;
		
		
		loop:
		for(int i=2; i<=n; i++) {
			if(stack.contains(i)) continue;
			int j = i;
			int t = 1;
			while(j<=n) {
				if(stack.contains(j)) {
					j=i*(++t);
					continue;
				}
				else {
					stack.push(j);
					cnt++;
					if(k==cnt) break loop;
					j=i*(++t);
				}
			}
		}
		System.out.println(stack.peek());
	}
}