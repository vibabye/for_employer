package com.ecomarket.WebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomarket.WebApp.models.Goods;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для доступа к сущности Goods в базе данных.
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    Goods getOne(Long id);
    Goods findByType(String type);


}

