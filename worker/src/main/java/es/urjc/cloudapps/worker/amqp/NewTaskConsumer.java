package es.urjc.cloudapps.worker.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.urjc.cloudapps.worker.clients.ExternalService1Client;
import es.urjc.cloudapps.worker.clients.ExternalService2Client;
import es.urjc.cloudapps.worker.dto.NewTaskRequest;
import es.urjc.cloudapps.worker.dto.TaskProgressResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NewTaskConsumer {

    private final ExternalService1Client external1Client;
    private final ExternalService2Client external2Client;

    private final TaskProgressProducer producer;

    private final ObjectMapper om;

    public NewTaskConsumer(ExternalService1Client external1Client,
                           ExternalService2Client external2Client,
                           TaskProgressProducer producer,
                           ObjectMapper om) {
        this.external1Client = external1Client;
        this.external2Client = external2Client;
        this.producer = producer;
        this.om = om;
    }

    @RabbitListener(queues = {"createTask"}, ackMode = "AUTO")
    public void newTaskConsumer(String msg) throws JsonProcessingException {

        NewTaskRequest request = om.readValue(msg, NewTaskRequest.class);

        Long id = request.getId();
        String word = request.getText();

        this.simulateProcessWaiting();
        this.advanceProcess(id, 25, false, null);

        CompletableFuture<String> toUpperCaseWord = external1Client.toUpperCase(word);

        toUpperCaseWord.thenAccept(w -> {
            this.advanceProcess(id, 50, false, null);
            CompletableFuture<String> translateWord = external2Client.translateWord(w);
            this.simulateProcessWaiting();
            translateWord.thenAccept(ww -> {
                String result = "*" + ww + "*";
                this.advanceProcess(id, 100, true, result);
            });
        });

    }

    private void advanceProcess(Long id, int progress, boolean completed, String result) {
        TaskProgressResponse response = new TaskProgressResponse(id, progress, completed, result);
        producer.publishTaskProgress(response);
    }

    private void simulateProcessWaiting() {
        int simulatedTime = 1000;
        try {
            Thread.sleep(simulatedTime);
        } catch (InterruptedException e) {
        }
    }

}
