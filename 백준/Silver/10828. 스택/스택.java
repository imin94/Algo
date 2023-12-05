import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.StringTokenizer;

import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException  {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		Stack<Integer> stc = new Stack<Integer>();

		int n = Integer.parseInt(br.readLine());

		for(int i=0; i<n; i++) {
			String str = br.readLine();
			if(str.equals("top")) {
				if(stc.size()==0)System.out.println(-1);
				else System.out.println(stc.peek());
			}
			else if(str.equals("empty")) {
				if(stc.empty()) System.out.println(1);
				else System.out.println(0);
			}
			else if(str.equals("size")) {
				System.out.println(stc.size());
			}
			else if(str.equals("pop")) {
				if(stc.empty()) System.out.println(-1);
				else System.out.println(stc.pop());
			}
			else {
				st = new StringTokenizer(str);
				st.nextToken();
				stc.push(Integer.valueOf(st.nextToken()));
				}
			}
	}
}