package homework.homework3_2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ИНФО",
        description = "Общая информация о проекте")
public class FirstController {
    @GetMapping
    public String startWebApp(){
        return "Приложение запущено";
    }
@GetMapping("/info")
@Operation(summary = "Получение информации о проекте")
    public String info(){
        return "имя ученика - Рияз, " +
                "название проекта - Homework3_2, " +
                "дата создания проекта - 03.02.2023, " +
                "описание проекта - Веб-приложение для добавления и получения рецептов.";
    }
}
