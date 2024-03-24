import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int R,C;
    public static int[][] arr;
    public static int[] start = {0,0};
    public static List<int[]> listF = new ArrayList<>();
    public static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[R][C];

        for(int r = 0; r<R; r++){
            String str = br.readLine();
            for(int c = 0; c<C; c++){
                char chr = str.charAt(c);
                if(chr == '#')
                    arr[r][c] = 1;
                else if(chr == '.')
                    arr[r][c] = 0;
                else if(chr =='F') {
                    arr[r][c] = 2;
                    listF.add(new int[] {r,c});
                }
                else {
                    start[0] = r;
                    start[1] = c;
                }
            }
        }
        int cnt = bfs();
        if(cnt==-1)
            bw.write("IMPOSSIBLE");
        else
            bw.write(String.valueOf(cnt));
        bw.close();
        br.close();
    }
    public static int bfs(){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {start[0],start[1],1});
        for(int[] f : listF)
        	que.add(new int[] {f[0],f[1],-1});

        boolean[][] flag = new boolean[R][C];
        flag[start[0]][start[1]] = true;

        while(!que.isEmpty()){
            int[] rc = que.poll();
            int r = rc[0];
            int c = rc[1];
            int cnt = rc[2];
            if(r==0 || r==R-1 || c==0 || c==C-1){
                if(arr[r][c]==0)
                    return cnt;
            }

            for(int i = 0; i<4; i++){
                int dr = r + delta[i][0];
                int dc = c + delta[i][1];

                if(dr<0 || dr>=R || dc<0 || dc>=C || arr[dr][dc]==1 || arr[dr][dc]==2)
                    continue;
                if(arr[r][c]==0) {
                    if(flag[dr][dc])
                        continue;
                    flag[dr][dc] = true;
                    que.add(new int[]{dr, dc, cnt+1});
                }
                else{
                    arr[dr][dc] = 2;
                    que.add(new int[]{dr,dc,-1});
                }
            }
        }

        return -1;
    }
}