package homework.homework3_2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String startWebApp(){
        return "Приложение запущено";
    }
@GetMapping("/info")
    public String info(){
        return "имя ученика - Рияз, " +
                "название проекта - Homework3_2, " +
                "дата создания проекта - 03.02.2023, " +
                "описание проекта - тестовый проект.";
    }
}
