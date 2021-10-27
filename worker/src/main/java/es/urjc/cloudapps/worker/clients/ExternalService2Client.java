package es.urjc.cloudapps.worker.clients;

import es.urjc.cloudapps.worker.dto.TranslatedWord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ExternalService2Client {

    @Value("${externalservice.two.host}")
    private String externalService2Host;

    @Value("${externalservice.two.port}")
    private String externalService2Port;

    @Async
    public CompletableFuture<String> translateWord(String word) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://" +
                externalService2Host + ":" + externalService2Port +
                "/api/translation/" + word;

        TranslatedWord response = restTemplate.getForObject(url, TranslatedWord.class);

        return CompletableFuture.completedFuture(response.getEnglish());
    }

}
