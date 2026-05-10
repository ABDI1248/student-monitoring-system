package com.school.studentstatusmonitoringsystem.repository;

import com.school.studentstatusmonitoringsystem.model.Role;
import com.school.studentstatusmonitoringsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 🔐 Find user for login
    Optional<User> findByUsername(String username);

    // 🔥 Advanced (optional - role filter)
    List<User> findByRole(Role role);

    // 🔍 Custom search (optional future use)
    @Query("SELECT u FROM User u WHERE " +
            "(:username IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))) AND " +
            "(:role IS NULL OR u.role = :role)")
    List<User> searchUsers(@Param("username") String username,
                           @Param("role") Role role);
}