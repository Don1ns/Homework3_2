package homework.homework3_2.services;

import java.io.File;

public interface FileService {
    boolean saveToFile(String json, String fileName);

    String readFromFile(String fileName);

    boolean cleanDataFile(String fileName);

    File getDataFile(String fileName);
}
