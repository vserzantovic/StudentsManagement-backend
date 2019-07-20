package vserzantovic.studentsmanagement.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vserzantovic.studentsmanagement.SortingType;
import vserzantovic.studentsmanagement.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Controller
public class FileUploadController {
    public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

    @RequestMapping("/")
    public String UploadPage(Model model) {
        EnumSet<SortingType> sortingTypes = EnumSet.allOf(SortingType.class);
        model.addAttribute("sortingTypes", sortingTypes);
        return "uploadView";
    }

    @RequestMapping("/upload")
    public String upload(Model model, @RequestParam("files") MultipartFile[] files, @RequestParam("sortingType") String sortingType) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename() + " ");
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            uploadFileDataToObject(String.valueOf(fileNameAndPath), model);
        }

        model.addAttribute("msg", "Succesfull uploaded files " + fileNames.toString());
        model.addAttribute("sortingType", "Selected order type are " + sortingType);

        return "uploadStatusView";

    }

    public void uploadFileDataToObject(String fileNameAndPath, Model model) throws IOException {
        List<Student> StudentList = new ArrayList<>();
        File f = new File(String.valueOf(fileNameAndPath));

        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {

                List<String> studentData = Arrays.asList(line.split(","));
                Student student = new Student();
                student.setName(studentData.get(0));
                student.setPoints(Float.parseFloat(studentData.get(1)));
                StudentList.add(student);
            }
            model.addAttribute("studentList", StudentList);
        } finally {
            reader.close();
        }

    }
}
