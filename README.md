# 동시성 처리를 위한 예제

- Java 11
- Spring boot 2.6.4
- Maria DB (docker-compose.yml 참고)

## Description
회원이 쿠폰을 다운로드 하는 기능으로 예제를 만들었습니다.   
같은 회원이 다운로드 제한이 걸린 쿠폰을 짧은 시간(ms) 안에 요청을 보낼 경우
동시성 처리를 하지 않으면 1장만 다운로드 가능한 쿠폰이 1장 이상을 다운받을 수 있게됩니다.

예제에서는 비관적 잠금으로 처리를 하였습니다.

### Point
```java
public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // 1
    List<MemberCoupon> findByMember_idAndCoupon_id(Long memberId, Long couponId);
}
```
해당 조건을 검색할 때 선점적으로 락을 걸어 다른 트랜잭션에서 접근하지 못하게 처리합니다.

### TEST
- CouponServiceTest
    - 쿠폰_다운로드()

### 
- ConcurrencyExampleApplication 에서 앱이 실행될 때 사용자 하나와 쿠폰 하나를 기본으로 생성해줍니다.


| h2도 `for update`를 지원한다고 되어있으나 왜인지 정상작동을 하지 않음   
| https://www.h2database.com/html/commands.html#select
