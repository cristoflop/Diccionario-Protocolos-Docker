"use strict"

const express = require("express");
const taskRouter = express.Router();
const taskController = require("../controller/taskController.js");

taskRouter.get("/tasks", taskController.findAll);
taskRouter.get("/tasks/:id", taskController.findById);
taskRouter.post("/tasks", taskController.save);
taskRouter.delete("/tasks/:id", taskController.deleteById);

module.exports = taskRouter;