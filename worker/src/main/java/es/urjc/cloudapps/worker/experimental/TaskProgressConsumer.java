package es.urjc.cloudapps.worker.experimental;

import es.urjc.cloudapps.worker.dto.TaskProgressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class TaskProgressConsumer {

    private final Logger log = LoggerFactory.getLogger(TaskProgressConsumer.class);

    @RabbitListener(queues = {"tasksProgress"}, ackMode = "AUTO")
    public void taskProgressConsumer(TaskProgressResponse response) {
        log.info("Recibida actualizacion de progresso: {}", response);
    }
}
