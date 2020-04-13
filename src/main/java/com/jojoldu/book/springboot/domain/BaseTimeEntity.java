package com.jojoldu.book.springboot.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 서브클래스들로 하여금 이 추상클래스의 컬럼들을 인식하게 함
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능을 추가한다.
public abstract class BaseTimeEntity {

    @CreatedDate // Entity 생성 시 시간이 자동 저장된다.
    private LocalDateTime createdDate; // Java 8 이후 LocalDateTime을 적극 활용하자. 기존 Date 클래스의 단점을 대폭 개선함

    @LastModifiedDate // Enitity의 값을 변경 시 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;

}
