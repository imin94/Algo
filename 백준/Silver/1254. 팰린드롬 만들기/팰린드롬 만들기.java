import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		
		bw.write(""+(rom(str)+(str.length()-rom(str))*2));
		br.close();
		bw.close();

	}
	//넣은 문자열 중 끝 문자를 기준으로 팰린드롬인 가장 긴 길이를 출력
	public static int rom(String str) {
		char[] chr = str.toCharArray();
		int result = 1;
		//뒤에서i번째 글자가 팰린드롬인가 확인
		for(int i = 0; i<chr.length; i++) {
			String x="";
			//뒤에서 i번째 문자까지 자르기
			for(int j=chr.length-1-i; j<chr.length; j++) {
				x+=chr[j];
			}
			//자른 문자가 팰린드롬인가?
			boolean tf = true;
			for(int j=0; j<x.length()/2; j++) {
				if(x.charAt(j)!=x.charAt(x.length()-1-j)) {
					tf=false;
					break;
				}
			}
			//맞으면 뒤에서 i+1 자리까지는 팰린드롬 
			if(tf)
				result=i+1;
		}
		//가장 긴 팰린드롬 자리를 출력
		return result;		
	}
}