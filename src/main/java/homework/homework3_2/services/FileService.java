package homework.homework3_2.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface FileService {
    boolean saveToFile(String json, String fileName);

    String readFromFile(String fileName);

    boolean cleanDataFile(String fileName);

    File getDataFile(String fileName);

    void uploadFile(MultipartFile file, String fileName) throws IOException;

    Path createTempFile(String suffix);
}
