package ru.aminovniaz.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aminovniaz.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
