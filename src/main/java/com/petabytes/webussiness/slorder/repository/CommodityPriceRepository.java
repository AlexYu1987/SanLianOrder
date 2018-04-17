package com.petabytes.webussiness.slorder.repository;

import com.petabytes.webussiness.slorder.entity.CommodityPrice;
import com.petabytes.webussiness.slorder.entity.PriceKey;
import org.springframework.data.repository.CrudRepository;

public interface CommodityPriceRepository extends CrudRepository<CommodityPrice, PriceKey> {
}
