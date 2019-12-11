package ru.molcom.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.molcom.domain.ProjectTask;
import ru.molcom.service.ProjectTaskService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/board")
@CrossOrigin
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("")
    public ResponseEntity<?> addPTToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        ProjectTask newPT = projectTaskService.saveOrUpdateProjectTask(projectTask);

        return new ResponseEntity<>(newPT, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public Iterable<ProjectTask> getAllPTs() {
        return projectTaskService.findAll();
    }

    @GetMapping("/{pt_id}")
    public ResponseEntity<?> getPTById(@PathVariable Long pt_id) {
        Optional<ProjectTask> item = projectTaskService.findById(pt_id);

        if (!item.isPresent())
            return new ResponseEntity<>("Project task did not exist", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ProjectTask>(item.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable Long pt_id) {
        projectTaskService.delete(pt_id);

        return new ResponseEntity<>("Project Task deleted", HttpStatus.OK);
    }
}