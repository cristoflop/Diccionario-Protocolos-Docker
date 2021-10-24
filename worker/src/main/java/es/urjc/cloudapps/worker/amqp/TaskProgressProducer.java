package es.urjc.cloudapps.worker.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskProgressProducer {

    private final RabbitTemplate rt;

    private final ObjectMapper om;

    public TaskProgressProducer(RabbitTemplate rt, ObjectMapper om) {
        this.rt = rt;
        this.om = om;
    }

    public void publishTaskProgress() {

    }

}
