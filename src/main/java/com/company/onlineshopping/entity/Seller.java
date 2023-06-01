package com.company.onlineshopping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "seller")

public class Seller extends BaseEntity {
    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "seller")
    private List<Product> products;
}
