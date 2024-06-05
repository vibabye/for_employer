package com.ecomarket.WebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomarket.WebApp.models.User;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для доступа к сущности User в базе данных.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    boolean existsByLogin(String login);

    User getOne(Long id);
    ;
}

