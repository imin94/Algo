import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Set<String> set = new HashSet<String>();
		Stack<String> stack = new Stack<String>();
		int answer = 0;
		int n = Integer.valueOf(br.readLine());

		for(int i = 0; i<n; i++) {
			String[] sArr = br.readLine().split("");
			int cnt = 1;
			set.clear();
			stack.clear();
			for(int j = 0; j<sArr.length; j++) {
				String chr = sArr[j];
				set.add(chr);
				if(stack.size()>0 && !stack.peek().equals(chr)) cnt++;
				stack.push(chr);
			}
			if(cnt==set.size()) answer++;
		}
		System.out.println(answer);
	}
}