package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 세션에 저장하기 위해 직렬화가 필요하지만, 엔티티 클래스에 직렬화를 선언할 경우 side effect / 성능 이슈가 발생할 수 있으므로 새 클래스를 선언한다
 */
@Getter
public class SessionUser implements Serializable{

    // 인증된 사용자 정보만 필요하다
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getEmail();
    }
}
