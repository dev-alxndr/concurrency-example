package com.example.concurrencyexample.repository;

import com.example.concurrencyexample.entity.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * @author : Alexander Choi
 * @date : 2022/03/09
 */
@Repository
public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<MemberCoupon> findByMemberIdAndCouponId(Long memberId, Long couponId);
}
