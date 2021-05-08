package uz.pdp.lesson10_task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.lesson10_task2.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}

