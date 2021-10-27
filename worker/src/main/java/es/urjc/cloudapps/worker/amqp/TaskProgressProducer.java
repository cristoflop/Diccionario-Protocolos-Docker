package es.urjc.cloudapps.worker.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.urjc.cloudapps.worker.dto.TaskProgressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskProgressProducer {

    private Logger log = LoggerFactory.getLogger(TaskProgressProducer.class);

    private final RabbitTemplate rt;
    private final ObjectMapper om;

    private final String topic = "tasksProgress";

    public TaskProgressProducer(RabbitTemplate rt, ObjectMapper om) {
        this.rt = rt;
        this.om = om;
    }

    public void publishTaskProgress(TaskProgressResponse response) {
        log.info("Actualizacion de progresso: \n {}", response);
        rt.convertAndSend(topic, response);
    }

}
