package vserzantovic.studentsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vserzantovic.studentsmanagement.controllers.FileUploadController;

import java.io.File;

@SpringBootApplication
public class StudentsManagementApplication {

    public static void main(String[] args) {
        new File(FileUploadController.uploadDirectory).mkdir();
        SpringApplication.run(StudentsManagementApplication.class, args);
    }



}
