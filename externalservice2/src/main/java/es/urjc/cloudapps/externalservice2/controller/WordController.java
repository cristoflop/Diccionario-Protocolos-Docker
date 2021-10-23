package es.urjc.cloudapps.externalservice2.controller;

import es.urjc.cloudapps.externalservice2.application.WordService;
import es.urjc.cloudapps.externalservice2.application.dto.WordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/translation/{word}")
    public ResponseEntity<Mono<WordDto>> translateWord(@PathVariable String word) {
        Mono<WordDto> translation = wordService.translateWord(word);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(translation);
    }

}
