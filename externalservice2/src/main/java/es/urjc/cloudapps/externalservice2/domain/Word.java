package es.urjc.cloudapps.externalservice2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Document(collection = "words")
public class Word {

    @Id
    private String spanish;

    private String english;

}
