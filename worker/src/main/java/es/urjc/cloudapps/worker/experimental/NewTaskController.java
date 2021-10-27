package es.urjc.cloudapps.worker.experimental;

import es.urjc.cloudapps.worker.amqp.NewTaskConsumer;
import es.urjc.cloudapps.worker.dto.NewTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class NewTaskController {

    @Autowired
    private NewTaskConsumer consumer;

    @GetMapping("/{id}/{word}")
    public void testService(@PathVariable Long id, @PathVariable String word) {
        NewTaskRequest request = new NewTaskRequest(id, word);
        consumer.newTaskConsumer(request);
    }

}
