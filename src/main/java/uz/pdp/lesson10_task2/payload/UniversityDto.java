package uz.pdp.lesson10_task2.payload;

import lombok.Data;

@Data
public class UniversityDto {//MA'LUMOTLARNI TASHISH UCHUN XIZMAT QILADI
    private String name;
    private String city;
    private String district;
    private String street;
}
