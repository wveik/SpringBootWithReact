package ru.molcom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.molcom.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
