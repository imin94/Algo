import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
 
public class Main {
 
	public static boolean[][] arr;
	public static int min = 64;
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
 
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
 
		arr = new boolean[N][M];
		
 
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				if (str.charAt(c) == 'W')
                    arr[r][c] = true;
				else
                    arr[r][c] = false;
			}
		}
 
		
		int N_row = N - 7;
		int M_col = M - 7;
 
		for (int r = 0; r < N_row; r++) {
			for (int c = 0; c < M_col; c++) {
				find(r, c);
			}
		}
		System.out.println(min);
	}
 
	
	public static void find(int x, int y) {
		int end_x = x + 8;
		int end_y = y + 8;
		int count = 0;
 
		boolean TF = arr[x][y];
 
		for (int r = x; r < end_x; r++) {
			for (int c = y; c < end_y; c++) {
				if (arr[r][c] != TF)
                    count++;
				TF = (!TF);
			}
			TF = !TF;
		}
		
		count = Math.min(count, 64 - count);
		
		min = Math.min(min, count);
	}
}