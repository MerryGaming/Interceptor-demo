package org.aibles.interceptor.repository;


import java.util.Optional;
import org.aibles.interceptor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByUsernameAndPassword(String username, String password);
}
