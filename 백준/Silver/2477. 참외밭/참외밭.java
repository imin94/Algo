import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Map<Integer,Integer> sabang = new HashMap<Integer, Integer>();
		int[][] place = new int[6][2];
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			place[i][0] = Integer.parseInt(st.nextToken());
			place[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i<6; i++) {
			if(sabang.containsKey(place[i][0]))
				sabang.put(place[i][0], 2);
			else
				sabang.put(place[i][0], 1);
		}
		
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		
		for(int i = 0; i<6; i++) {
			if(sabang.get(place[i][0])==1 && x1==0)
				x1=place[i][1];
			else if(sabang.get(place[i][0])==1 && x1!=0)
				y1=place[i][1];
			if(place[i%6][0]==place[(i+2)%6][0] && place[(i+1)%6][0]==place[(i+3)%6][0]) {
				x2=place[(i+1)%6][1];
				y2=place[(i+2)%6][1];
			}
		}
		
		bw.write(""+((x1*y1-x2*y2)*n));
		br.close();
		bw.close();
		
	}

}