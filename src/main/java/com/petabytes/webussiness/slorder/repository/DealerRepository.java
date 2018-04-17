package com.petabytes.webussiness.slorder.repository;

import com.petabytes.webussiness.slorder.entity.Dealer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DealerRepository extends CrudRepository<Dealer, Long> {

    /**
     * 扣费
     * @param id
     * @param amount
     * @return 余额
     */
    @Modifying
    @Query("UPDATE Dealer SET Balance = Balance - ?2 WHERE ID = ?1")
    void chargeBalanceById(long id, float amount);

    /**
     * 充值
     * @param id
     * @param amount
     * @return 余额
     */
    @Modifying
    @Query("UPDATE Dealer SET Balance = Balance + ?2 WHERE ID = ?1")
    void rechargeBalanceById(long id, float amount);
}
