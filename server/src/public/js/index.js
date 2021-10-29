"use strict"

const socket = io();

socket.on("connection", () => {
    console.log("Nuevo cliente conectado! =]");
    rechargeTasks();
});

socket.on("updateTasks", () => {
    rechargeTasks();
});

socket.on("enableBtn", () => {
    document.querySelector("#add-btn").disabled = false;
});

socket.on("disableBtn", () => {
    document.querySelector("#add-btn").disabled = true;
});

/***********************************************************************************************************************************************/

function addTask() {
    const text = document.querySelector("#forWord").value.trim();
    if (text === "") {
        alert("No has introducido ninguna palabra");
    } else {
        save("/api/tasks", {text: text})
            .then(response => {
                if (response.status === 201) {
                    socket.emit("disableBtn");
                    rechargeTasks();
                } else {
                    alert("Error al crear la tarea");
                }
            });
    }
}

function removeTask(buttonClicked) {
    const taskId = buttonClicked.value;
    if (taskId === undefined || taskId === "") {
        alert("Error en el id");
    } else {
        remove(`/api/tasks/${taskId}`)
            .then(response => {
                if (response.status === 204) {
                    rechargeTasks();
                } else {
                    alert("No se puede borrar la tarea");
                }
            });
    }
}

/***********************************************************************************************************************************************/

function rechargeTasks() {
    read("/api/tasks")
        .then(response => {
            const taskList = document.querySelector("#task-list");
            taskList.innerHTML = response.data.map(task => {
                return renderLiFromTask(task);
            }).join("\n");
        });
}

function renderLiFromTask(task) {
    const result = task.completed ? task.result : "EN PROCESO";
    const deleteButton =
        `<button onClick="removeTask(this)" class="btn btn-raised btn-danger" value="${task.id}">Eliminar</button>`;
    return `<li class="mi-li mi-border shadow p-2 mb-2 bg-white rounded">
                <div id="task-info"><strong>${result}</strong> :: ${task.progress}%</div>
                ${deleteButton}
            </li>`
}

/***********************************************************************************************************************************************/

async function read(url) {
    const headers = new Headers();
    headers.append("Content-type", "application/json");
    const response = await fetch(url, {
        "headers": headers,
    });
    const result = await response.json();
    return {
        status: response.status,
        data: result
    }
}

async function save(url, data) {
    const headers = new Headers();
    headers.append("Content-type", "application/json");
    const response = await fetch(url, {
        "headers": headers,
        "method": "POST",
        "body": JSON.stringify(data)
    });
    return {
        status: response.status,
        data: response.json()
    }
}

async function remove(url) {
    const headers = new Headers();
    headers.append("Content-type", "application/json");
    const response = await fetch(url, {
        "headers": headers,
        "method": "DELETE",
    });
    return {
        status: response.status
    }
}
