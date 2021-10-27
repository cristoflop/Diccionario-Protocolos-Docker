package es.urjc.cloudapps.worker.experimental;

import es.urjc.cloudapps.worker.dto.TaskProgressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TaskProgressConsumer {

    private final Logger log = LoggerFactory.getLogger(TaskProgressConsumer.class);

    @RabbitListener(queues = {"taskProgress"}, ackMode = "AUTO")
    public void taskProgressConsumer(TaskProgressResponse response) {
        log.info("Recibida actualizacion de progresso: \n {}", response);
    }
}
