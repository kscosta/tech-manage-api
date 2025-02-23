package com.jobtest.techmanager.infrastructure.repository;

import com.jobtest.techmanager.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório de Usuários
 *
 * @version 1.0
 * @since Java 21
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
