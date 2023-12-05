import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        for(int i = 1; i<=N; i++){
            if (i<100)
                cnt++;
            else if(i<1000){
                int baek = i/100;
                int ship = i/10%10;
                int il = i%10;
                if((baek-ship)==(ship-il))
                    cnt++;
            }
        }
        bw.write(String.valueOf(cnt));
        bw.close();
    }
}