package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;    //기본 생성자

import javax.persistence.*;
/**
 * Entity : 테이블과 링크될 클래스
 *          DB Layer(테이블 구조와 동일)
 */
/*Entity 클래스에는 절대 Setter메소드를 만들지 않음
인스턴스 값들이 어디서 변해야하는지 코드상으로 명확하게 구분할 수 없음

기본적으로 생성자를 통해 값을 채운 후
필드 값 변경이 필요한 경우 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 사용
 ex) public void cancelOrder(){this.status=false;}*/
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {//table : sales_manager => clasee : SalesManager)

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성 규칙 - spring boot 2.0 이후 부터 (strategy = GenerationType.IDENTITY) 선언해야만 auto imcrement됨
    private Long id;

    //기본값 외에 추가로 변경이 필요한 옵션이 있는 경우 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //빌더 패턴 클래스 생성
    //생성자에 포함된 필드만 빌더에 포함
    /*클래스가 setter 메소드 없이 getter 메소드만 가진다는 것과 public 생성자가 없다는 것입니다. 그렇기 때문에 Posts 객체를 얻기 위해서는 오직 Builder 클래스를 통해서만 가능*/
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
