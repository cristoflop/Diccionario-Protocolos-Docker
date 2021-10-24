package es.urjc.cloudapps.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskProgressResponse {

    private int id;
    private String word;
    private int progress;

}
