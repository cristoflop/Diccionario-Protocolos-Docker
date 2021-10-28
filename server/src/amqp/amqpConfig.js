"use strict"

const amqp = {
    url: "amqp://guest:guest@localhost:5674",
    queues: {
        createTask: "createTask",
        tasksProgress: "tasksProgress"
    }
};

module.exports = amqp;