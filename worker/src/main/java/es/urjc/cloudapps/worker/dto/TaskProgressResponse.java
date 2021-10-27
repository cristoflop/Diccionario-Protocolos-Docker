package es.urjc.cloudapps.worker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskProgressResponse {

    private Long id;
    private int progress;
    private boolean completed;
    private String result;

}
