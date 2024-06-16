import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int m;
    public static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i<n; i++)
            list.add(Integer.parseInt(br.readLine()));

        int l = 1;
        int r = Integer.MAX_VALUE;
        while(l<=r){
            int mid = (l+r) >>> 1;
            if(find(mid))
                r=mid-1;
            else
                l=mid+1;
        }
        bw.write(l+"\n");
        br.close();
        bw.close();
    }

    public static boolean find(int k){
        int cnt = 1;
        int cost = k;
        for(int i = 0; i<list.size(); i++){
            if(list.get(i)>k)
                return false;
            else if(list.get(i)<=cost)
                cost-=list.get(i);
            else {
                cost = k;
                cost-=list.get(i);
                cnt++;
            }
        }
        if(cnt<=m)
            return true;
        return false;
    }
}