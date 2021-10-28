package es.urjc.cloudapps.worker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskProgressResponse implements Serializable {

    private Long id;
    private int progress;
    private boolean completed;
    private String result;

}
