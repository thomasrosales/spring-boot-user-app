package com.example.usersapp.repositories;

import com.example.usersapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);

    @Query(nativeQuery = true, value="SELECT r.* FROM roles AS r INNER JOIN users_roles AS ur ON ur.role_id = r.id WHERE ur.user_id = ?1")
    List<Role> findRolesByUserId(String userId);
}
