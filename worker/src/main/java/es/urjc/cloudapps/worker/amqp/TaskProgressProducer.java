package es.urjc.cloudapps.worker.amqp;

import es.urjc.cloudapps.worker.dto.TaskProgressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskProgressProducer {

    private Logger log = LoggerFactory.getLogger(TaskProgressProducer.class);

    private final RabbitTemplate rt;

    private final String topic = "tasksProgress";

    public TaskProgressProducer(RabbitTemplate rt) {
        this.rt = rt;
    }

    public void publishTaskProgress(TaskProgressResponse response) {
        log.info("Actualizacion de progresso: {}", response);
        rt.convertAndSend(topic, response);
    }

}
