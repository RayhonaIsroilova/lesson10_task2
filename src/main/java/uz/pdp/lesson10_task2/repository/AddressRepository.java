package uz.pdp.lesson10_task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson10_task2.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}

