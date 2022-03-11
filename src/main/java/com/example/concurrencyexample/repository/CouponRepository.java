package com.example.concurrencyexample.repository;

import com.example.concurrencyexample.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Alexander Choi
 * @date : 2022/03/09
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
