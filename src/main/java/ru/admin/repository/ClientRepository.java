package ru.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.admin.model.FintesClient;

public interface ClientRepository extends JpaRepository<FintesClient, Integer> {
}
