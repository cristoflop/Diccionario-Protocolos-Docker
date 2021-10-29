"use strict"

const amqp = require("amqplib/callback_api");
const amqpConfig = require("./amqpConfig.js");

const taskController = require("../controller/taskController.js");

const CONN_URL = amqpConfig.url;
const queueName = amqpConfig.queues.tasksProgress;

function consume(io) {
    amqp.connect(CONN_URL, async function (err, connection) {
        const channel = await connection.createChannel();

        channel.assertQueue(queueName, {durable: false});

        channel.consume(queueName, async (buffer) => {
            console.log(`Nuevo mensaje recibido: ${buffer.content}`);
            const content = JSON.parse(buffer.content.toString());
            console.log(`---> Actualizar tarea con id ${content.id}`);
            await taskController.updateById(content);
            io.sockets.emit("updateTasks");
            if (content.progress === 100) {
                io.sockets.emit("enableBtn");
            }
            channel.ack(buffer);
        });
    });
}

module.exports = {
    consume
}