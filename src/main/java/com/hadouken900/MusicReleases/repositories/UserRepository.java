package com.hadouken900.MusicReleases.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hadouken900.MusicReleases.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
