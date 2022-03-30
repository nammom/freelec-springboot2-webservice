package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor//생성자로 Bean주입 - final이 붙은 필드를 인자값으로 하는 생성자
@Service
public class PostsService {

    //@AutoWired 대신 @RequiredArgsConstructor 사용
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * 더티 체킹 :
     *
     * 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면
     * 이 데이터는 영속성 컨텍스트가 유지된 상태
     * 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        //데이터 베이스에서 데이터를 가져옴 -> 영속성 컨텍스트가 유지
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        //해당 데이터의 값을 변경 -> 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        return new PostsResponseDto(entity);
    }

    //트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회속도가 개선됨(오직 조회기능만 있는 경우 사용)
    @Transactional(readOnly = true)
    public Optional<List<PostsListResponseDto>> findAllDesc() {
        return Optional.of(postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList()));
    }

    @Transactional
    public void delete(Long id) {
       Posts posts = postsRepository.findById(id)
               .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

       postsRepository.delete(posts);
    }
}
