package es.urjc.cloudapps.worker.experimental;

import com.fasterxml.jackson.core.JsonProcessingException;
import es.urjc.cloudapps.worker.amqp.NewTaskConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class NewTaskController {

    private final Logger log = LoggerFactory.getLogger(NewTaskController.class);

    @Autowired
    private NewTaskConsumer consumer;

    @GetMapping("/{id}/{word}")
    public void testService(@PathVariable Long id, @PathVariable String word) throws JsonProcessingException {
        String msg = "{\"id\": " + id + ", \"text\": \"" + word + "\"}";
        // NewTaskRequest request = new NewTaskRequest(id, word);
        log.info("New test request: {}", msg);
        consumer.newTaskConsumer(msg);
    }

}
