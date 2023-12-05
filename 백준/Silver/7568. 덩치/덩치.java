import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	StringTokenizer st;
	
	int n = Integer.parseInt(br.readLine());
	int[][] dungch = new int[n][2];
	int[] answer = new int[n];
	int w = 0, h = 0;
	
	for(int i = 0; i<n; i++) {
		st= new StringTokenizer(br.readLine());
		dungch[i][0] = Integer.parseInt(st.nextToken());
		dungch[i][1] = Integer.parseInt(st.nextToken());
	}
	
	for(int i =0; i<n; i++) {
		answer[i]++;
		for(int j = 0; j<n; j++) {
			if(dungch[i][0]<dungch[j][0] && dungch[i][1]<dungch[j][1])
				answer[i]++;
		}
	}
	
	for(int i = 0; i<answer.length; i++) {
		System.out.print(answer[i]+" ");
	}
	
	}
}