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
			//게시물 작성
			else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String regDate = util.getNowTimeStr();	//현재시간출력을 위해 클래스 가져다쓴다.
				
//				Article article = new Article(id, title, body);
				Article article = new Article(id, regDate, title, body);
				articles.add(article);

				lastArticleId = id;

				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			} 
			
			//게시물 리스트, 여기서 조회수 나타냄, 카운트는 클래스안에서 한다.
			else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				//System.out.println("번호, 제목");		//조회수위해 수정됨
				System.out.println("번호 | 조회 | 제목");
				//size() 함수는 해당 ArrayList의 크기를 알 수있는 명령어
				//get()함수는 해당 번지의 값을 가져오는 명령어이다.
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					//System.out.printf("%d, %s\n", article.id, article.title);	//조회수 추가
					System.out.printf("%4d | %4d | %s\n", article.id, article.hit,article.title);
				}
				
				
			}
			//equals는 서로 문장이 똑같은가 판단.
			//startsWith 는 앞에 초반만 곂치는지 판단한다.
			//split 은 쪼개다로 else if문의 조건단어를 공백을 기준으로 짜른다.
			//예) article' 'detail' '133 ---> 3등분으로 짜름
			
			//게시물 상세페이지
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
				
					foundArticle.increaseHit(); //상세페이지 들어갈시 증가시킴.
					System.out.printf("번호 : %d \n", id);
					System.out.printf("날짜 : %s \n", foundArticle.regDate);
					System.out.printf("제목 : %s \n", foundArticle.title);
					System.out.printf("내용 : %s \n", foundArticle.body);
					System.out.printf("조회 : %d \n", foundArticle.hit);
					continue;
				
				
			}
			//게시물 삭제
			else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				int foundIndex = -1;	//foundArticle을 이용 기사를 가져오는게 아니라 삭제할려고 하니
										// 인덱스를 만들어 id주소만 대조 후 삭제하기위한 변수.

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
			
			//게시물 수정
			else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;		//내용 수정위함.
				foundArticle.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

			
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
	String regDate;
	int hit;	//조회수변수
	
	public Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = 0;	//초기값
	
	}
	
	public void increaseHit() {
		hit++;	//함수 호출시 조회수 추가위함.
	}
}