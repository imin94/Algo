import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] listStr = new String[n];
        int[] listNum = new int[n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            listStr[i] = st.nextToken();
            listNum[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<m; i++){
            int num = Integer.parseInt(br.readLine());
            int l = 0;
            int r = n-1;
            while(l<=r) {
                int mid = (l+r) >>> 1;
                if(num<=listNum[mid])
                    r = mid-1;
                else
                    l = mid+1;
            }

            sb.append(listStr[l]+"\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}