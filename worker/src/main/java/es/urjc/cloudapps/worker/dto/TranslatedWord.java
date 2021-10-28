package es.urjc.cloudapps.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TranslatedWord {

    private String id;
    private String translation;

}
