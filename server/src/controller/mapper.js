"use strict"

function taskMapper(task) {
    if (task instanceof Array)
        return task.map(item => taskMapper(item));
    else
        return {id: task._id, progress: task.progress, completed: task.completed, result: task.result};
}

function basicTaskMapper(task) {
    return {id: task._id};
}

module.exports = {taskMapper, basicTaskMapper}