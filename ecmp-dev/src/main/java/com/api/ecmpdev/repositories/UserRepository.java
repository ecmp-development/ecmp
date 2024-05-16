package com.api.ecmpdev.repositories;

import com.api.ecmpdev.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @Modifying
    @Query("""
update User u set u.password = :password where u.id = :id
""")
    void insertNewPassword(@Param("id") Long id, @Param("password") String password);

    @Modifying
    @Query("""
update User u set u.email = :email where u.id = :id
""")
    void insertNewEmail(@Param("id") Long id, @Param("email") String email);
}
