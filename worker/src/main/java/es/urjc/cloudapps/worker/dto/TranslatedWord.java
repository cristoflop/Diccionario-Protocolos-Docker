package es.urjc.cloudapps.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class TranslatedWord implements Serializable {

    private String spanish;
    private String english;

}
