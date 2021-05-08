package uz.pdp.lesson10_task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson10_task2.entity.Address;
import uz.pdp.lesson10_task2.entity.Group;
import uz.pdp.lesson10_task2.entity.Student;
import uz.pdp.lesson10_task2.payload.StudentDTO;
import uz.pdp.lesson10_task2.repository.AddressRepository;
import uz.pdp.lesson10_task2.repository.GroupRepository;
import uz.pdp.lesson10_task2.repository.StudentRepository;
import uz.pdp.lesson10_task2.repository.SubjectRepository;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SubjectRepository subjectRepository;

    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAll(pageable);
    }

    //2. UNIVERSITY
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId,
                                                     @RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
    }

    //3. FACULTY DEKANAT
    @GetMapping("/forDekan/{dekanId}")
    public Page<Student> getStudentForDekan(@PathVariable Integer dekanId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_FacultyId(dekanId, pageable);
    }

    //4. GROUP OWNER
    @GetMapping("/forGroupOwner/{groupOwnerId}")
    public Page<Student> getStudentForGroupOwner(@PathVariable Integer groupOwnerId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroupId(groupOwnerId, pageable);
    }

    //CREATE
    @PostMapping("/adding")
    public String addingStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        Optional<Address> byId = addressRepository.findById(studentDTO.getAddressId());
        if (!byId.isPresent()) {
            return "such address not found";
        }
        student.setAddress(byId.get());
        Optional<Group> byId1 = groupRepository.findById(studentDTO.getGroupId());
        if (!byId1.isPresent()) {
            return "such group not found";
        }
        student.setGroup(byId1.get());
        studentRepository.save(student);
        return "Student added";
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            Student student = byId.get();
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            Optional<Address> byId1 = addressRepository.findById(studentDTO.getAddressId());
            if (!byId1.isPresent()) {
                return "Address not found";
            }
            student.setAddress(byId1.get());
            Optional<Group> byId2 = groupRepository.findById(id);
            if (!byId2.isPresent()) {
                return "Group not found";
            }
            student.setGroup(byId2.get());
            studentRepository.save(student);
            return "Student edited";
        }
        return "Student not found";
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        try {
            studentRepository.deleteById(id);
            return "Student deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }

}

