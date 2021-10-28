"use strict"

const Task = require("../models/task.js");
const mapper = require("./mapper.js");

const producer = require("../amqp/newTaskProducer.js");

async function findAll(req, res) {
    const tasks = await Task.findAll();
    res.status(200);
    res.json(mapper.taskMapper(tasks));
}

async function findById(req, res) {
    const id = req.params.id;

    const task = await Task.findOne({where: {_id: id}});
    if (task == null) {
        res.status(404);
        res.json({message: "Task not found"});
        return;
    }

    res.status(200);
    res.json(mapper.taskMapper(task));
}

async function save(req, res) {
    const text = req.body.text;

    const task = await Task.create({
        completed: false,
        progress: 0,
        result: null
    });

    await producer.publish({id: task._id, text: text});

    res.status(201);
    res.json(mapper.basicTaskMapper(task));
}

async function deleteById(req, res) {
    const id = req.params.id;

    const task = await Task.findOne({where: {_id: id}});
    if (task == null) {
        res.status(404);
        res.json({message: "Task not found"});
        return;
    }

    await Task.destroy({where: {_id: id}});

    res.status(204);
    res.json({message: "Task successfully removed"});
}

async function updateById(task) {
    const id = task.id;

    const savedTask = await Task.findOne({where: {_id: id}});
    if (savedTask == null) {
        return;
    }

    await Task.update(
        {
            progress: task.progress,
            completed: task.completed,
            result: task.result
        },
        {
            where: {_id: id}
        });
}

module.exports = {
    findAll,
    findById,
    save,
    deleteById,
    updateById
}