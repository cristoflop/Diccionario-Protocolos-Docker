package es.urjc.cloudapps.worker.amqp;

import es.urjc.cloudapps.worker.dto.NewTaskRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NewTaskConsumer {

    private final TaskProgressProducer producer;

    public NewTaskConsumer(TaskProgressProducer producer) {
        this.producer = producer;
    }

    @RabbitListener(queues = {"createTask"}, ackMode = "AUTO")
    public void newTaskConsumer(NewTaskRequest request) {

    }

}
