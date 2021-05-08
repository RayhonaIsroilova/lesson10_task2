package uz.pdp.lesson10_task2.payload;

import lombok.Data;

@Data
public class StudentDTO {
    private String firstName, lastName;
    private Integer addressId, groupId;

}

