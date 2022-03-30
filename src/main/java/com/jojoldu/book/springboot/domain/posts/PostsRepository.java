package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * DB Layer 접근자 
 * -Dao역할
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
    //JpaRepository<Entity 클래스, PK타입> 상속하면 기본적인 CRUD 메소드가 생성됨
    //!! 주의 !! Entity 클래스와 기본 Entity Repository는 함께 위치해야함

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    public List<Posts> findAllDesc();
}
