import java.io.*;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		String str = br.readLine();
		int len = str.length();
		for(int i = 0; i<len; i++) {
			if(str.charAt(i)=='<') {
				int idx = i;
				while(str.charAt(idx)!='>') {
					sb.append(str.charAt(idx++));
				}
				sb.append(str.charAt(idx));
				i=idx;
			}
			else if(str.charAt(i)==' ')
				sb.append(' ');
			else {
				int idx = i;
				while(idx<len && str.charAt(idx)!=' ' && str.charAt(idx)!='<') {
					stack.add(str.charAt(idx++));
				}
				i=idx-1;
				while(stack.size()>0) {
					sb.append(stack.pop());
				}
				stack.clear();
			}
		}
		
		bw.write(sb.toString().trim());
		br.close();
		bw.close();
	}

}