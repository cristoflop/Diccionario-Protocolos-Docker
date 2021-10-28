"use strict"

const amqp = require("amqplib");
const amqpConfig = require("./amqpConfig.js");

const CONN_URL = amqpConfig.url;
const queueName = amqpConfig.queues.createTask;

async function publish(data) {
    const connection = await amqp.connect(CONN_URL);

    const channel = await connection.createChannel();
    await channel.assertQueue(queueName, {durable: false});

    const json = JSON.stringify(data);

    const sent = await channel.sendToQueue(queueName, Buffer.from(json), {persistent: false});
}

module.exports = {
    publish
}