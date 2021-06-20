package cl.schedulebackend.repository;

import cl.schedulebackend.model.Schedule;
import cl.schedulebackend.projection.CustomSchedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(excerptProjection = CustomSchedule.class)
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    @Query(value = "SELECT id, title, description FROM SCHEDULES", nativeQuery = true)
    List<CustomSchedule> findAllCustomQuery();

}
