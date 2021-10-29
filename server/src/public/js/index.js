"use strict"

/***********************************************************************************************************************************************/

function addTask() {
    const text = document.getElementById("forWord").value.trim();
    if (text === "") {
        alert("No has introducido ninguna palabra");
    } else {
        save("/api/tasks", {text: text})
            .then(response => {
                if (response.status === 201) {
                    // document.getElementById("add-btn").disabled = true;
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
        read(`/api/tasks/${taskId}`, true)
            .then(response => {
                if (response.status === 204) {
                    rechargeTasks();
                } else {
                    alert(response.data.message);
                }
            });
    }
}

/***********************************************************************************************************************************************/

function rechargeTasks() {
    read("/api/tasks")
        .then(response => {
            const taskList = document.getElementById("task-list");
            taskList.innerHTML = response.data.map(task => {
                return renderLiFromTask(task);
            }).join("\n");
        });
}

function renderLiFromTask(task) {
    const result = task.completed ? task.result : "EN PROCESO";
    // const completed = eoloPlant.completed ? `<strong>COMPLETADO</strong>` : "";
    const deleteButton =
        `<button onClick="removeTask(this)" class="btn btn-raised btn-danger" value="${task.id}">Eliminar</button>`;
    return `<li class="mi-li mi-border shadow p-2 mb-2 bg-white rounded">
                <div id="task-info"><strong>${result}</strong> :: ${task.progress}%</div>
                ${deleteButton}
            </li>`
}

/***********************************************************************************************************************************************/

async function read(url, isDelete) {
    const headers = new Headers();
    headers.append("Content-type", "application/json");
    const response = await fetch(url, {
        "headers": headers,
        "method": isDelete !== undefined ? "DELETE" : "GET"
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
        status: response.status
    }
}

async function remove(url, data) {
    const headers = new Headers();
    headers.append("Content-type", "application/json");
    const response = await fetch(url, {
        "headers": headers,
        "method": "DELETE",
        "body": JSON.stringify(data)
    });
    return {
        status: response.status
    }
}

/***********************************************************************************************************************************************/

window.onload = function () {
    rechargeTasks();
}