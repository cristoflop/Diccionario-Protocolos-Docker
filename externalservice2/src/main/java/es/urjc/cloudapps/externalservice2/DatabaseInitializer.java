package es.urjc.cloudapps.externalservice2;

import es.urjc.cloudapps.externalservice2.domain.Word;
import es.urjc.cloudapps.externalservice2.repository.WordRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    private WordRepository wordRepository;

    public DatabaseInitializer(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @PostConstruct
    public void setUp() {

        wordRepository.deleteAll();

        Flux<Word> words = Flux.just(
                new Word("casa", "house"),
                new Word("coche", "car"),
                new Word("mesa", "table"),
                new Word("silla", "chair"),
                new Word("puerta", "door"),
                new Word("portatil", "laptop"),
                new Word("cama", "bed"),
                new Word("armario", "wardrobe"),
                new Word("lapiz", "pencil"),
                new Word("boli", "pen"),
                new Word("moneda", "coin"),
                new Word("papel", "paper"),
                new Word("circulo", "circle"),
                new Word("cuadraro", "square"),
                new Word("cable", "wire"),
                new Word("luz", "light"),
                new Word("paquete", "package")
        );

        words.flatMap(this.wordRepository::save).blockLast();
    }

}
