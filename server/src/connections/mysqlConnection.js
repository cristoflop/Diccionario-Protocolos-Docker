"use strict"

const Sequelize = require('sequelize');

const sequelize = new Sequelize(
    'dictionary',
    'root',
    'pass',
    {
        host: 'localhost',
        port: 3306,
        dialect: "mysql"
    }
);

process.on('exit', async () => {
    await sequelize.close();
    console.log(`Closing mysql connection`);
});

module.exports = sequelize;