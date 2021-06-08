package com.database.aim.controller;

import com.database.aim.pojo.Task;
import com.database.aim.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
}
