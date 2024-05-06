/**
 * To query data from table role 
 * 
 * @author Leakhena SUON
 * @version 1.0
 * @since 2024-04-30
 */

package com.user.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.models.ERole;
import com.user.management.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
