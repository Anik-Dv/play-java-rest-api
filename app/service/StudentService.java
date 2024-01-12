package service;

import dao.StudentDao;
import dto.StudentDto;
import map.StudentMapper;
import models.Student;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentService {

    @Inject
    private StudentDao studentDao;

    public List<StudentDto> getAll() {
        List<Student> studentList = studentDao.findStudents();
        return studentList.stream().map(student ->
                StudentMapper.EntityToTransferDto(student)).collect(Collectors.toList());
    }

    public Optional<StudentDto> getById(Long id) {
        Student student = studentDao.findStudent(id);
        if (student == null) {
            return Optional.empty();
        }
        return Optional.of(StudentMapper.EntityToTransferDto(student));
    }

    public StudentDto createStudent(StudentDto studentDTO) {
        Student student = StudentMapper.DtoToTransferEntity(studentDTO);
        student = studentDao.createStudent(student);
        return StudentMapper.EntityToTransferDto(student);
    }

    public Optional<StudentDto> updateStudent(Long id, StudentDto studentDTO) {
        Student studentFromDb = studentDao.findStudent(id);
        if (studentFromDb == null) {
            return Optional.empty();
        }
        // set new properties
        studentFromDb.setName(studentDTO.getName());
        studentFromDb.setAge(studentDTO.getAge());
        studentFromDb.setDepartment(studentDTO.getDepartment());
        studentFromDb = studentDao.updateStudent(studentFromDb);

        return Optional.of(StudentMapper.EntityToTransferDto(studentFromDb));
    }

    public boolean deleteStudent(Long id) {
        Optional<StudentDto> studentDto = this.getById(id);
        if (studentDto.isPresent()) {
            studentDao.deleteStudent(id);
            return true;
        } else {
            return false;
        }
    }

}
