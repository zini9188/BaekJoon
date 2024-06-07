import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			//출력 값을 저장할 문자열.
			String S = "Pairs for " + n + ": ";
			
			for(int j = 1; j <= n / 2; j++) {
				
				//쌍이 서로 같지 않을때만 문자열 추가
				if(j != (n - j)) {
					
					if(j > 1) {
						S += ", ";
					}
					S += j + " " + (n - j);
				}
			}
			//출력
			System.out.println(S);
		}
	}

}