package uz.mehrojbek.appbookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mehrojbek.appbookshop.entity.Client;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
