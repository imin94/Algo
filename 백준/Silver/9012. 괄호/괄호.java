import java.io.*;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<String> stack = new Stack<String>();
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i<n; i++) {
			stack.clear();
			String[] VPS = br.readLine().split("");
			for(String j : VPS) {
				if(stack.size()>0 && stack.peek().equals("(") && j.equals(")"))
					stack.pop();
				else
					stack.add(j);
				
			}
			if(stack.size()==0)
				sb.append("YES"+'\n');
			else
				sb.append("NO"+'\n');
		}
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}