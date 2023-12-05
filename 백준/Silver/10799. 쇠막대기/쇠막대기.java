import java.io.*;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		int len = str.length();
		int cnt = 0;
		char lastChr = 0;
		for(int i = 0; i<len; i++) {
			char chr = str.charAt(i);
			if(chr==')') {
				if(lastChr==')') {
					cnt++;
					stack.pop();
				}
				else {
					stack.pop();
					cnt+=stack.size();
					lastChr=chr;
				}
					
			}
			else {
				stack.add(chr);
				lastChr=chr;
			}
		}
		
		System.out.print(cnt);
		
	}

}