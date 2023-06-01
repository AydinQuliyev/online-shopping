package com.company.onlineshopping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "customers")

public class Customer extends BaseEntity {
    @Column(name = "user_id")
    private Long userId;
}
