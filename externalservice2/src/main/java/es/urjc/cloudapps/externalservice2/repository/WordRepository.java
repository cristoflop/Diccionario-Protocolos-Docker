package es.urjc.cloudapps.externalservice2.repository;

import es.urjc.cloudapps.externalservice2.domain.Word;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WordRepository extends ReactiveCrudRepository<Word, String> {

    Mono<Word> findBySpanishIgnoreCase(String spanish);

}
