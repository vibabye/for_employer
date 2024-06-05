package com.ecomarket.WebApp.repository;

import com.ecomarket.WebApp.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для доступа к сущности Basket в базе данных.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
