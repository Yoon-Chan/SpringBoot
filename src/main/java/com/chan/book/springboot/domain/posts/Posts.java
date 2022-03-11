package com.chan.book.springboot.domain.posts;

import com.chan.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Getter/NoArgsConstructor는 롬복의 어노테이션이다.
@Getter
@NoArgsConstructor
@Entity //JPA의 어노테이션 테이블과 링크될 클래스임을 나타낸다. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭.
public class Posts extends BaseTimeEntity {    //Posts클래스는 실제 DB의 테이블과 매칭될 클래스 보통 Entity 클래스라고도한다.

    @Id //해당 테이블의 PK 필드를 나타낸다. 기본키.
    //PK의 생성 규칙을 나타낸다. GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됩니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Column은 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    //사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용.(예: 기본값은 VARCHAR(255)인데,
    //사이즈 500으로 늘릴떄, 타입을 TEXT로 변경하고 싶을 때 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //Builder는 해당 클래스의 빌더 패턴 클래스 생성.
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content= content;
        this.author= author;
    }

    public void update(String title, String content){
        this.title= title;
        this.content = content;
    }
}
