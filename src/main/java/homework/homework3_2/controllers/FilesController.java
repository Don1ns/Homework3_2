package homework.homework3_2.controllers;

import homework.homework3_2.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    @Value("${name.of.ingredients.data.file}")
    private String ingredientsDataFileName;
    @Value("${name.of.recipes.data.file}")
    private String recipesDataFileName;
    private final FileService fileService;


    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }
    @Operation(
            summary = "Скачивание рецептов в виде json-файла"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл получен"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Нет контента")
    })
    @GetMapping("/export_recipes")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = fileService.getDataFile(recipesDataFileName);

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @Operation(
            summary = "Замена файла с рецептами на загруженный"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл заменен"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Что-то пошло не так")
    })
    @PostMapping("/import_recipes")
    public ResponseEntity<Void> uploadRecipesDataFile(@RequestParam MultipartFile file){
        fileService.cleanDataFile(recipesDataFileName);
        File dataFile = fileService.getDataFile(recipesDataFileName);

        try (FileOutputStream fos = new FileOutputStream(dataFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @Operation(
            summary = "Замена файла с рецептами на загруженный"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл заменен"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Что-то пошло не так")
    })
    @PostMapping("/import_ingredients")
    public ResponseEntity<Void> uploadIngredientsDataFile(@RequestParam MultipartFile file){
        fileService.cleanDataFile(ingredientsDataFileName);
        File dataFile = fileService.getDataFile(ingredientsDataFileName);

        try (FileOutputStream fos = new FileOutputStream(dataFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
