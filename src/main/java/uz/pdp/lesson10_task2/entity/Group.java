package uz.pdp.lesson10_task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne//MANY group TO ONE faculty
    private Faculty faculty;
//
//    @OneToMany//ONE group TO MANY students
//    private List<Student> students;

}
