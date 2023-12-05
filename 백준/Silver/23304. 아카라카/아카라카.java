import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		bw.write(palind(str));
		br.close();
		bw.close();
	}
	
	public static String palind(String str) {
		int len = str.length();
		int mid = len/2;
		String ans = "AKARAKA";
		
		for(int i = 0; i<mid; i++) {
			if(str.charAt(i)!=str.charAt(len-1-i)) {
				return "IPSELENTI";
			}
		}

		if(ans.equals("AKARAKA") && len>1) {
			ans=palind(str.substring(0,mid));
		}
		
		return ans;
		
	}

}