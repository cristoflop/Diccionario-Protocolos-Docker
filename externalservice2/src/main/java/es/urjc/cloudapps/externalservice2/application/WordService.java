package es.urjc.cloudapps.externalservice2.application;

import es.urjc.cloudapps.externalservice2.application.dto.WordDto;
import es.urjc.cloudapps.externalservice2.domain.Word;
import es.urjc.cloudapps.externalservice2.repository.WordRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Mono<WordDto> translateWord(String word) {
        Mono<Word> existingWord = wordRepository.findBySpanishIgnoreCase(word)
                .delayElement(Duration.ofMillis(1000 + new Random().nextInt(2000)))
                .switchIfEmpty(Mono.just(new Word("", "unknown")));

        return existingWord.map(this::toWordDto);

    }

    private WordDto toWordDto(Word word) {
        return new WordDto(
                word.getSpanish(),
                word.getEnglish().toUpperCase()
        );
    }

}
