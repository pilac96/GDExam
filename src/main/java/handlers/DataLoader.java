package handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Student;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataLoader {

    public static List<Student> loadStudents(String pathToJsonData) {
        ObjectMapper mapper = new ObjectMapper();
        List<Student> students = null;
        try {
            students = mapper.readValue(new File(pathToJsonData), new TypeReference<List<Student>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }


}
