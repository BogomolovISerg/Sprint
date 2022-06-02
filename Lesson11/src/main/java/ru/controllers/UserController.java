package ru.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.entities.User;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("")
    public String editUsersList() {
        return "Списком пользователей";
    }

    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable Long userId) {
        return "Редактирование данных о пользователе с id = " + userId;
    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user) {
        return "Сохранить данные о пользователе: " + user.getUsername() + " " + user.getEmail();

    }

    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody User user) {
        return "Изменить данные о пользователе " + user.getUsername() + " " + user.getEmail();
    }

    @DeleteMapping(value = "/edit/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@PathVariable Long userId) {
        return "Удалить данные о пользователе id = " + userId;
    }
}
