import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		
		if(num>=90)
			bw.write("A");
		else if(num>=80)
			bw.write("B");
		else if(num>=70)
			bw.write("C");
		else if(num>=60)
			bw.write("D");
		else
			bw.write("F");
		
		br.close();
		bw.close();
	}

}