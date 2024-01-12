package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import models.Student;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.List;

public class StudentDao {

    private static final String ENTITY_MANAGER_NAME = "default";

    @Inject
    protected JPAApi jpaApi;

//    create student
    public Student createStudent(Student student) {
        jpaApi.withTransaction(entityManager -> { entityManager.persist(student); });
        return student;
    }

    // get an student
    public Student findStudent(Long id) {
        return jpaApi.em(ENTITY_MANAGER_NAME).find(Student.class, id);
    }

    // find all students
    public List<Student> findStudents() {
        EntityManager entityManager = jpaApi.em(ENTITY_MANAGER_NAME);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    // delete an student
    //@Transactional
    public void deleteStudent(Long id) {
        jpaApi.withTransaction(entityManager -> {
            Student student = entityManager.find(Student.class, id);
            if (student != null) {
                // then remove from db
                entityManager.remove(student);
            }
        });
    }

    // update student
    public Student updateStudent(Student student) {
        // if student is exists then marge it with new info
        jpaApi.withTransaction(entityManager -> {entityManager.merge(student);});
        return student;
    }

}
