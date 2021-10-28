"use strict"

const amqp = require("amqplib/callback_api");
const amqpConfig = require("./amqpConfig.js");

const CONN_URL = amqpConfig.url;
const queueName = amqpConfig.queues.tasksProgress;

function consume() {
    amqp.connect(CONN_URL, async function (err, connection) {
        const channel = await connection.createChannel();

        channel.assertQueue(queueName, {durable: false});

        channel.consume(queueName, (buffer) => {
            console.log(`Nuevo mensaje recibido: ${buffer.content}`);
            const content = JSON.parse(buffer.content.toString());
            console.log(content);
            channel.ack(buffer);
        });
    });
}

module.exports = {
    consume
}