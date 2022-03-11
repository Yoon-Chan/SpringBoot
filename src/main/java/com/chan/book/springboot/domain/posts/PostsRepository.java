package com.chan.book.springboot.domain.posts;
//Posts클래스로 Database를 접근하게 해줄 JpaRepository

import com.chan.book.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//단순히 인터페이스르 생성 후, JpaRepository<Entity클래스, PK타입>을 상속하면 기본적인 CRUD메소드가 자동으로 생성됩니다.
//@Repository를 추가할 필요가 없다. 주의할 점은 Entity클래스와 기본 Entity Repository는 함께 위치해야한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {


    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
