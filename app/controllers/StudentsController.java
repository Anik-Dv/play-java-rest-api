package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.StudentService;

import javax.inject.Inject;
import java.util.Optional;


public class StudentsController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(StudentsController.class);

    @Inject
    private StudentService studentService;

    // api for get single student
    public Result getSingleStudent(Long id) {
        try {
            Optional<StudentDto> student = studentService.getById(id);
            if (student.isPresent()) {
                return ok(Json.toJson(student.get()));
            }
            return notFound("SORRY! Not Found Any Students!");
        } catch (Exception e) {
            e.printStackTrace();
            return notFound("SORRY! Not Found Any Students!");
        }
    }

    // api for get All student
    public Result getAllStudent() {
        try {
            return ok(Json.toJson(studentService.getAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return notFound("SORRY! Not Found Any Students!");
        }
    }

    public Result createStudent(Http.Request request) {
        JsonNode jsonData = request.body().asJson();
        try {
            StudentDto createdStudent = Json.fromJson(jsonData, StudentDto.class);
            createdStudent = studentService.createStudent(createdStudent);
            return ok(Json.toJson(createdStudent));
        } catch(Exception e) {
            e.printStackTrace();
            return badRequest(request.body().asJson().get("SORRY! Your Request Can't Proceed!"));
        }
    }

    public Result updateStudent(Long studentId, Http.Request request) {
        JsonNode jsonData = request.body().asJson();
        try {
            StudentDto student = Json.fromJson(jsonData, StudentDto.class);
            Optional<StudentDto> updatedStudent = studentService.updateStudent(studentId, student);
            if (updatedStudent.isPresent()) {
                return ok(Json.toJson(updatedStudent.get()));
            }
            return notFound(Json.toJson("Student Has Not Found of This Student Id : " + studentId));
        } catch(Exception e) {
            e.printStackTrace();
            return badRequest(Json.toJson("SORRY! Your Request Can't Proceed!"));
        }
    }

    public Result deleteStudent(Long studentId) {
        try {
            boolean deletedStudent = this.studentService.deleteStudent(studentId);
            if (deletedStudent) {
                return ok(Json.toJson("Student Was Deleted Successfully."));
            } else {
                return notFound(Json.toJson("SORRY! Student Is Not Found!"));
            }
        } catch(Exception e) {
            e.printStackTrace();
            return badRequest("SORRY! Your Request Can't Proceed!");
        }
    }


}
