import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int[][] place;
    public static boolean[][] used;
    public static int R;
    public static int C;
    public static int max = Integer.MIN_VALUE;
    public static int[][] sabang = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        place = new int[R][C];
        used = new boolean[R][C];
        for(int r = 0; r<R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c<C; c++) {
                place[r][c]=Integer.parseInt(st.nextToken());
            }
        }
        //좌표별 4칸 모형을 움직이 점수를 획득하는 반복문
        for(int r = 0; r<R; r++) {
            for(int c = 0; c<C; c++) {
            	//r,c좌표에서 탐색 시작, r,c 좌표는 다시 돌아오지 못하게 true처리
                used[r][c]=true;
                //r,c는 탐색 하였으니, depth=1, sum=place[r][c]
                dfs(r,c,1,place[r][c]);
                //해당 r,c 좌표에 대해서 4칸의 경우를 모두 보았으면 다음칸으로 이동하기 전 false로 원복
                used[r][c]=false;
            }
        }
        System.out.print(max);
    }
    
    public static void dfs(int r, int c, int depth, int sum) {
    	//4칸을 모두 보았으면, max값과 비교
        if(depth==4) {
            if(sum>max)
                max=sum;
            return;
        }
        //r,c을 기점으로 사방 탐색
        for(int i = 0; i<4; i++) {
            int dr = r+sabang[i][0];
            int dc = c+sabang[i][1];
            //범위를 벗어나거나, 이미 탐색해온 좌표인 경우 보지 않음
            if(dr<0 || dr>=R || dc<0 || dc>=C || used[dr][dc])
                continue;
            //ㅜ,ㅏ,ㅗ,ㅓ 모양을 보기 위해 depth==2일 때는 좌표는 고정하고, depth와 sum값을 증가하며, 해당 좌표는 true처리
            if(depth==2) {
                used[dr][dc]=true;
                dfs(r,c,depth+1,sum+place[dr][dc]);
                used[dr][dc]=false;
            }
            //ㅜ,ㅏ,ㅗ,ㅓ 외의 모양은 기존과 같이 진행하면 된다.
            used[dr][dc]=true;
            dfs(dr,dc,depth+1,sum+place[dr][dc]);
            used[dr][dc]=false;
        }
    }

}