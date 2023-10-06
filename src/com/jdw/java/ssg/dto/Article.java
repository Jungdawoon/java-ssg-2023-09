package com.jdw.java.ssg.dto;

//메인에 클래스를 옮겼음. 클래스 우클릭후, reforter 클릭후 move type to newfile 클릭해서 옮김
//옮긴 후 class및 변수에 public설정해줘야 사용가능
public class Article extends Dto {
	
	//DTO라고한다
	
	public String title;
	public String body;
	public int hit;	//조회수변수
	
	//hit를 안넣은버전
	public Article(int id, String regDate, String title, String body) {
//		this.id = id;
//		this.regDate = regDate;
//		this.title = title;
//		this.body = body;
//		this.hit = 0;	//초기값
		//중복을 피하기위해 안의 변수를 this호출로 바꿈
		this(id, regDate, title, body, 0);	//밑에 hit넣은 버전으로 토스한다.
	
	}
	
	//hit를 넣은버전.
	public Article(int id, String regDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;	//초기값
	
	}
	
	public void increaseHit() {
		hit++;	//함수 호출시 조회수 추가위함.
	}
}