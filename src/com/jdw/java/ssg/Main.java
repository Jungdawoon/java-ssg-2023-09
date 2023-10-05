package com.jdw.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 		10/5 수업 equals, startsWith 배움
 * 		
 * 
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 == ");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;	//게시물 번호

		List<Article> articles = new ArrayList();
		
		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			command = command.trim();	//문자열 양옆 공백제거
			
			//다른 명령어 입력시 처음부터 출력하고자 할때  
			if (command.length() == 0) {
				continue;
			}

			if (command.equals("system exit")) {
				break;
			} 
			else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);
				articles.add(article);

				lastArticleId = id;

				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			} 
			else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				System.out.println("번호, 제목");
				//size() 함수는 해당 ArrayList의 크기를 알 수있는 명령어
				//get()함수는 해당 번지의 값을 가져오는 명령어이다.
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%d, %s\n", article.id, article.title);
				}
			}
			//equals는 서로 문장이 똑같은가 판단.
			//startsWith 는 앞에 초반만 곂치는지 판단한다.
			//split 은 쪼개다로 else if문의 조건단어를 공백을 기준으로 짜른다.
			//예) article' 'detail' '133 ---> 3등분으로 짜름
			else if (command.startsWith("article detail")) {
				String[] commandBits = command.split(" "); //안에 ',' 넣을 경우 ,기준으로 지금은 공백기준으로 할려고 띄움
														  // String을 배열로 설정해준다.--> 공백으로 나눈 단어들을 넣어줄려고
				int id = Integer.parseInt(commandBits[2]); // Integer.parseInt는 문자를 숫자로 변경해준다.
				// 결과적으로 id에는 숫자가 들어간다. commandBits에는 문장이 들어가 있으니
				/*
				 * commandBits[0]	-- article
				 * commandBits[1]	-- detail
				 * commandBits[2]	-- 1 (숫자들어가 있는 방)
				 */
				//boolean found =  false; //코드정리시 필요없는 구문됨.
				Article foundArticle = null;	//해당게시물 몇번째인지 알기위해
				
				//articles는 게시물들이 있는게 아니라 게시물리모콘들이 있는것
				// id부분은 0,1,2,3,4 일때 내가적은 부분(1부터시작)과 같은지 판단한다.
				//			1,2,3,4,5 -->내가 적은것
				for(int i = 0; i<articles.size(); i++) {
					Article article = articles.get(i);
					if(article.id == id) {
						//found = true;
						foundArticle = article;	
						
						break;
					}
				}
				//foundArticle이 애초에 null이면 게시물이 없다는 의미이기 때문에 found구문이 필요없어짐.
			
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
			
				//여기서 else를 주석처리한 이유는 위 if문에 continue구문이 있기때문에 굳이 else할 필요가 없다.
				//else 
				{
					System.out.printf("번호 : %d \n", id);
					System.out.printf("날짜 : %s \n", "2020-12-12");
					System.out.printf("제목 : %s \n", foundArticle.title);
					System.out.printf("내용 : %s \n", foundArticle.body);
					continue;
				}
				
			}
			else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				int foundIndex = -1;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}
				//foundIndex로 인해 다시 바뀜, -1일경우 id가 없다는 의미
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				//게시물 삭제
				// size() 	--> 3
				// index	-->0,1,2,
				// id		-->1,2,3,
				
				articles.remove(foundIndex);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
			}
			else {
				System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
			}
		}

		sc.close();
		System.out.println("== 프로그램 끝 == ");
	}
}

class Article {
	int id;
	String title;
	String body;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}