package homework.homework3_2.services;

public interface FileService {
    boolean saveToFile(String json, String fileName);

    String readFromFile(String fileName);

}
