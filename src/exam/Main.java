package exam;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("====프로그램 시작===");
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("명령어) ");
		
		String commond = sc.next();
		
		System.out.println("입력된 명령어 : "+commond);
		sc.close();//다 사용했다
		System.out.println("====프로그램 끝===");

	}

}