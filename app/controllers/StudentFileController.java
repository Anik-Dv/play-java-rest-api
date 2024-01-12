package controllers;

import models.Student;
import play.db.jpa.JPAApi;
import javax.inject.Inject;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.libs.Files.TemporaryFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class StudentFileController extends Controller {

    private static final String ENTITY_MANAGER_NAME = "default";
    @Inject
    protected JPAApi jpaApi;

    // UPLOAD FILE
    public Result uploadFile(Http.Request request) {
        Http.MultipartFormData<TemporaryFile> body = request.body().asMultipartFormData();
        Http.MultipartFormData.FilePart<TemporaryFile> filePart = body.getFile("file");

        if (filePart != null) {
            TemporaryFile file = filePart.getRef();
            String fileName = filePart.getFilename();
            String contentType = filePart.getContentType();

            // Generate a unique filename to avoid conflicts
            String uniqueFileName = UUID.randomUUID().toString() + "-" + fileName;

            // Define the directory where you want to store the uploaded files
            String uploadDirectory = "public/images/";

            try {
                // Create the directory if it doesn't exist
                Files.createDirectories(Paths.get(uploadDirectory));

                // Move the uploaded file to the upload directory
                Path targetPath = Paths.get(uploadDirectory, uniqueFileName);
                file.copyTo(targetPath, true);

                Student student = new Student();
                student.setAssignmentName(uniqueFileName);
                // Save students assignment name to the database
                jpaApi.withTransaction(entityManager -> {
                        entityManager.merge(student);
                });
                // Return a success response with the file details
                return ok("File uploaded successfully: " + uniqueFileName);
            } catch (IOException e) {
                // Handle IO exception
                return internalServerError("Failed to upload file: " + e.getMessage());
            }
        } else {
            // No file found in the request
            return badRequest("File upload failed");
        }
    }

    // DOWNLOAD FILE
    public Result downloadFile(String fileName) {
        // Define the directory where you want to store the uploaded files
        String uploadDirectory = "public/images/";

        // Create a File object for the requested file
        File file = new File(uploadDirectory + fileName);

        if (file.exists()) {
            // Return the file for download
            return ok(file).as("application/octet-stream");
        } else {
            // File not found
            return notFound("File not found: " + fileName);
        }
    }

}

