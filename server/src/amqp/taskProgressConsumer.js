"use strict"

const amqp = require("amqplib/callback_api");
const amqpConfig = require("./amqpConfig.js");

const CONN_URL = amqpConfig.url;
const queueName = amqpConfig.queues.tasksProgress;

function consume() {
    amqp.connect(CONN_URL, async function (err, connection) {
        const chanel = await connection.createChannel();

        chanel.assertQueue(queueName);

        chanel.consume(queueName, (buffer) => {
            const content = JSON.parse(buffer.content.toString());
            console.log(content);
            chanel.ack(buffer);
        });
    });
}

module.exports = {
    consume
}