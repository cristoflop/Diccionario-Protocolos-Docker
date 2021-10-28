package es.urjc.cloudapps.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NewTaskRequest implements Serializable {

    private Long id;
    private String text;

}
