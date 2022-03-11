package com.example.concurrencyexample;

import com.example.concurrencyexample.entity.Coupon;
import com.example.concurrencyexample.entity.Member;
import com.example.concurrencyexample.repository.CouponRepository;
import com.example.concurrencyexample.repository.MemberRepository;
import com.example.concurrencyexample.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class ConcurrencyExampleApplication implements ApplicationRunner {

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;

    private final CouponService couponService;

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyExampleApplication.class, args);
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        memberRepository.save(Member.createMember("USER"));
        couponRepository.save(Coupon.createCoupon("COUPON", 3));

//        couponService.downloadCoupon();
    }
}
