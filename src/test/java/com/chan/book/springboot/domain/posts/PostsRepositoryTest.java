package com.chan.book.springboot.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//여기에서는 다음과 같이 save, findAll 기능을 테스트합니다.
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //After: Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정.
    //보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
    //여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 다음 테스트 실행 시 테스트가 실패할수 있음.
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    //게시글 저장 불러오기
    @Test
    public void post_load(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본분";

        //테이블 posts에 insert/update 쿼리를 실행한다.
        postsRepository.save(Posts.builder().title(title).content(content).author("vsvx13@naver.com").build());

        //when
        //postsRepository.findAll() : 테이블 posts에 있는 모든 데이터를 조회해오는 메소드입니다.
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}
