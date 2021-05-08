package uz.pdp.lesson10_task2.payload;

import lombok.Data;
import uz.pdp.lesson10_task2.entity.Subject;

import java.util.List;

@Data
public class StudentDTO {
    private String firstName, lastName;
    private Integer addressId, groupId;
    private List<Subject> subjectsId;
}

