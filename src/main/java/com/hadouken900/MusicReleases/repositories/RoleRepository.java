package com.hadouken900.MusicReleases.repositories;

import com.hadouken900.MusicReleases.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}