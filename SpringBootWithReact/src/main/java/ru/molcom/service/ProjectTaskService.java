package ru.molcom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.molcom.domain.ProjectTask;
import ru.molcom.repository.ProjectTaskRepository;

import java.util.Optional;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask) {

        if (StringUtils.isEmpty(projectTask.getStatus())) {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findAll() {
        return projectTaskRepository.findAll();
    }

    public void delete(Long id) {
        projectTaskRepository.deleteById(id);
    }

    public Optional<ProjectTask> findById(Long id) {
        return projectTaskRepository.findById(id);
    }
}