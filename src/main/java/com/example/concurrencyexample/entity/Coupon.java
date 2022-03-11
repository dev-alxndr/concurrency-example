package com.example.concurrencyexample.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : Alexander Choi
 * @date : 2022/03/09
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String name;

    private Integer limitOf;

    public static Coupon createCoupon(String name, Integer limitOf) {
        return Coupon.builder()
                .name(name)
                .limitOf(limitOf)
                .build();
    }

}
