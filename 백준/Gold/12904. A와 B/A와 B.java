import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String S = br.readLine();
		String T = br.readLine();
		
		//문자열의 양쪽의 포인트를 잡고 A인지 B인지에 따라 포인트를 이동시킬 예쩡
		int s = 0;
		int e = T.length()-1;
		//시작점은 맨 마지막 단어부터 역순으로 추척해 나감
		char chr = T.charAt(e);
		//방향 0: <-, 1: ->
		int direction = 0;
		int len = S.length();
		
		//S문자 길이랑 같아지면 멈춤, s가 0부터 시작하기에 +1
		while(e-s+1>len) {
			if(chr=='A' && direction == 0) {
				e--;
				chr = T.charAt(e);
			}
			else if(chr=='A' && direction == 1) {
				s++;
				chr = T.charAt(s);
			}
			else if(chr=='B' && direction == 0){
				e--;
				chr = T.charAt(s);
				direction = 1;
			}
			else if(chr=='B' && direction == 1) {
				s++;
				chr = T.charAt(e);
				direction = 0;
			}
			else
				break;
		}
		
		//종료되었을 때 방향이 0:-> 이라면 s부터 e까지 문자열 생성
		if(direction == 0) {
			for(int i = s; i<=e; i++)
				sb.append(T.charAt(i));
		}
		//방향이 1: <- 이라면 e부터 s까지 문자열 생성(뒤집어서)
		else {
			for(int i = e; i>=s; i--)
				sb.append(T.charAt(i));
		}
		
		int ans = sb.toString().equals(S) ? 1 : 0; 
		bw.write(String.valueOf(ans));
		br.close();
		bw.close();
		
	}

}