import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();   
		
		
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			//실행할 행동 R과 D를 나누어서 action String[] 배열에 저장
			String[] action = br.readLine().split("");
			sb.append("[");
			//배열의 길이
			int n = Integer.parseInt(br.readLine());
			//배열 생성
			List<Integer> iArr = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine().replace("[", "").replace("]", ""),",");
			for(int j=0; j<n; j++) {
				iArr.add(Integer.parseInt(st.nextToken()));
			}
			//reverse가 나올 경우 r이 바뀐다, true: 뒤에서 부터 삭제, false: 앞에서 부터 삭제 
			boolean r = false;
			//error 호출 해야하는 경우 true 할당
			boolean e = false;
			//배열의 왼쪽에서 제외할 idx
			int lCnt=0;
			//배열의 오른쪽에서 제외할 idx
			int rCnt=iArr.size()-1;
			//action에 입력된 동작만큼 for문 시행
			for(int j=0; j<action.length; j++) {
				if(action[j].equals("R"))
					r=!r;
				else if(action[j].equals("D") && lCnt>rCnt) {
					//에러 발생할 경우 [을 삭제
					sb.deleteCharAt(sb.length()-1);
					//초기화한 sb에 에러 작성
					sb.append("error").append('\n');
					//error 발생 시 continue를 위해 idx 재할당
					e=!e;
					break;
				}
				else if(action[j].equals("D") && !r)
					lCnt++;
				else if(action[j].equals("D") && r)
					rCnt--;
			}
			//error 발생 시 continue
			if(e)
				continue;
			//iArr에 들어있는 인자값을 다 뺀 경우 ]닫고 continue
			else if(lCnt>rCnt) {
				sb.append("]").append('\n');
				continue;
			}
			//남은 iArr 출력, idx가 0보다 큰 경우 역으로 출력
			else if(r) {
				for(int h=rCnt; h>=lCnt; h--) {
					sb.append(iArr.get(h)).append(",");
				}
				if(sb.charAt(sb.length()-1)==',')
						sb.deleteCharAt(sb.length()-1);
				sb.append("]").append('\n');
			}
			else {
				for(int h=lCnt; h<=rCnt; h++) {
					sb.append(iArr.get(h)).append(",");
				}
				if(sb.charAt(sb.length()-1)==',')
					sb.deleteCharAt(sb.length()-1);
				sb.append("]").append('\n');
			}
		}
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}