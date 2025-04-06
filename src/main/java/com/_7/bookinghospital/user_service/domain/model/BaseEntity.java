package com._7.bookinghospital.user_service.domain.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    @CreatedDate
    protected LocalDateTime createdAt;

    @CreatedBy
    protected Long createdBy;

    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @LastModifiedBy
    protected Long updatedBy;

    protected boolean is_deleted;

    // delete() 메서드 호출시 updatedAt 과 updatedBy 자동으로 데이터 입력됨.
    public void delete(Long deletedBy) {
        this.is_deleted = true;
    }
}

