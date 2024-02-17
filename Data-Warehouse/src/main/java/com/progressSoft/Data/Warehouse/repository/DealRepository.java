package com.progressSoft.Data.Warehouse.repository;

import com.progressSoft.Data.Warehouse.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    boolean existsByDealUniqueId(String dealUniqueId);

    Optional<Deal> findByDealUniqueId(String dealUniqueId);
}
