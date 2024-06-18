import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int[] list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        list = new int[m];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++)
            list[i]=Integer.parseInt(st.nextToken());

        int l = 1;
        int r = 1000000000;
        while(l<=r){
            int mid = (l+r) >>> 1;
            if(find(mid))
                l=mid+1;
            else
                r=mid-1;
        }
        bw.write(r+"\n");
        br.close();
        bw.close();
    }

    public static boolean find(int k){
        int cnt = 0;
        for(int i = 0; i<list.length; i++){
            cnt += list[i]/k;
            if(cnt>=n)
                return true;
        }
        return false;
    }
}