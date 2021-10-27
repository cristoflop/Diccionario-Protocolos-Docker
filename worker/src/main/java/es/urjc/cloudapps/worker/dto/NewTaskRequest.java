package es.urjc.cloudapps.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewTaskRequest {

    private Long id;
    private String text;

}
