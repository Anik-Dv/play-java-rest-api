package map;


import dto.StudentDto;
import models.Student;

public class StudentMapper {

    // this is map entity to dto
    public static StudentDto EntityToTransferDto(Student entity) {
        StudentDto dto = new StudentDto();
        dto.setStudentId(entity.getStudentId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setDepartment(entity.getDepartment());
        return dto;
    }


    // this is map entity to dto
    public static Student DtoToTransferEntity(StudentDto dto) {
        Student entity = new Student();
        entity.setStudentId(dto.getStudentId());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setDepartment(dto.getDepartment());
        return entity;
    }

}
