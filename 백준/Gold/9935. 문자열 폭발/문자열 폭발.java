import java.io.*;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Character> ans = new Stack<>();
		Stack<Character> temp = new Stack<>();
		Stack<Character> boom = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		char[] chr = br.readLine().toCharArray();
		char[] boomChr = br.readLine().toCharArray();

		for(int i = boomChr.length-1; i>=0; i--) {
			boom.add(boomChr[i]);
		}
		int size = boom.size();
		for(int i = 0; i<chr.length; i++) {
			ans.add(chr[i]);
			if(ans.size()>=size && ans.peek().equals(boom.get(0))) {
				for(int j=0; j<size; j++) {
					temp.add(ans.pop());
				}
				if(temp.equals(boom))
					temp.clear();
				else
					for(int j=0; j<size; j++) {
						ans.add(temp.pop());
					}
			}
		}
		if(ans.size()>0) {
			int i = 0;
			while(i<ans.size()) {
				bw.write(ans.get(i));
				i++;
			}
		}
		else
			bw.write("FRULA");
		br.close();
		bw.close();
	}
}