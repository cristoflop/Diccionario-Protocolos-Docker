"use strict"

const {Model, DataTypes} = require('sequelize');
const sequelize = require("../connections/mysqlConnection");

class Task extends Model {
}

Task.init({
    _id: {
        type: DataTypes.BIGINT,
        autoIncrement: true,
        primaryKey: true
    },
    progress: {
        type: DataTypes.INTEGER
    },
    completed: {
        type: DataTypes.BOOLEAN
    },
    result: {
        type: DataTypes.STRING,
        allowNull: true
    }
}, {
    sequelize,
    modelName: 'task'
});

sequelize.sync();

module.exports = Task;