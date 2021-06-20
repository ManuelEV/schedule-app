package cl.schedulebackend.projection;


import cl.schedulebackend.model.Schedule;
import org.springframework.data.rest.core.config.Projection;


@Projection(name = "customSchedule", types = Schedule.class)
public interface CustomSchedule {

    Long getId();

    String getTitle();

    String getDescription();

}
