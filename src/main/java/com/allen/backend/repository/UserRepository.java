package com.allen.backend.repository;

import com.allen.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //Todo Hacer el repository
}