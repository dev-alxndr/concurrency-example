package com.example.concurrencyexample.service;

import com.example.concurrencyexample.entity.Coupon;
import com.example.concurrencyexample.entity.Member;
import com.example.concurrencyexample.entity.MemberCoupon;
import com.example.concurrencyexample.repository.CouponRepository;
import com.example.concurrencyexample.repository.MemberCouponRepository;
import com.example.concurrencyexample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author : Alexander Choi
 * @date : 2022/03/09
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CouponService {

    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final MemberCouponRepository memberCouponRepository;


    @Transactional
    public void downloadCoupon() {

        Optional<Member> optMember = memberRepository.findById(1L);
        Member member = optMember.orElseThrow(() -> new IllegalArgumentException("NOT EXISTS COUPON"));

        Optional<Coupon> optCoupon = couponRepository.findById(1L);

        Coupon coupon = optCoupon.orElseThrow(() -> new IllegalArgumentException("NOT EXISTS COUPON"));
        Integer limitOf = coupon.getLimitOf();

        List<MemberCoupon> memberCoupons = memberCouponRepository.findByMember_idAndCoupon_id(member.getId(), coupon.getId());

        String threadName = Thread.currentThread().getName();
        log.info("{} / coupon size = {}", threadName, memberCoupons.size());

        if (memberCoupons.size() < limitOf) {
            MemberCoupon newMemberCoupon = MemberCoupon.createMemberCoupon(member, coupon);
            memberCouponRepository.save(newMemberCoupon);
        }

    }


}
