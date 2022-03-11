package com.example.concurrencyexample.service;

import com.example.concurrencyexample.entity.Coupon;
import com.example.concurrencyexample.entity.MemberCoupon;
import com.example.concurrencyexample.repository.MemberCouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Alexander Choi
 * @date : 2022/03/09
 */
@SpringBootTest
class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private MemberCouponRepository memberCouponRepository;


    @Test
    public void 쿠폰_다운로드() throws Exception {

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            CountDownLatch latch = new CountDownLatch(5);
            for (int i = 0; i < 5; i++) {
                executorService.execute(() -> {
                    String name = Thread.currentThread().getName();
                    System.out.println("current thread name = " + name);

                    couponService.downloadCoupon();
                });
                latch.countDown();
            }

            latch.await();

            List<MemberCoupon> memberCoupons = memberCouponRepository.findByMember_idAndCoupon_id(1L, 1L);
            assertEquals(memberCoupons.size(), 1);


        } catch (RuntimeException re) {
            re.getStackTrace();
        }
    }
}
