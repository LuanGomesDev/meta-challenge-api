package br.com.challenge.meta.repository;

import br.com.challenge.meta.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository
    extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {}
