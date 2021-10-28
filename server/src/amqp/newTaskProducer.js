"use strict"

const amqp = require("amqplib");
const amqpConfig = require("./amqpConfig.js");

const CONN_URL = amqpConfig.url;
const queueName = amqpConfig.queues.createTask;

async function publish(data) {
    const connection = await amqp.connect(CONN_URL);

    const chanel = await connection.createChannel();
    await chanel.assertQueue(queueName, {durable: false});

    const json = JSON.stringify(data);

    const sent = await chanel.sendToQueue(queueName, Buffer.from(json)/*, {persistent: true}*/); // se persiste si durable es true
}

module.exports = {
    publish
}