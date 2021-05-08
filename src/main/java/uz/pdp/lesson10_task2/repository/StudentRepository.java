package uz.pdp.lesson10_task2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson10_task2.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {


    Page<Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);

    Page<Student> findAllByGroup_FacultyId(Integer group_facultyId, Pageable pageable);

    Page<Student> findAllByGroupId(Integer group_id, Pageable pageable);
}

