"use strict"

const express = require('express');
const path = require("path");
const app = express();

const staticFiles = path.join(__dirname, "public");
const bodyParser = require("body-parser");
const cookieParser = require("cookie-parser");

const taskRouter = require("./routes/taskRouter");

app.use(express.json());
app.use(express.static(staticFiles));
app.use(bodyParser.urlencoded({extended: false}));
app.use(cookieParser());

app.use("/api", taskRouter);

const server = app.listen(3000, err => {
    if (err)
        console.error(`No se ha podido iniciar el servidor: ${err.message}`);
    else
        console.log(`Servidor arrancado en el puerto 3000`);
});

const io = require("socket.io")(server);

const consumer = require("./amqp/taskProgressConsumer.js");
consumer.consume(io);

app.set("io", io);

io.on("connection", socket => {

    socket.emit("connection");

    socket.on("disableBtn", () => {
        socket.emit("disableBtn");
    });

});