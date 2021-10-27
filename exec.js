const {spawn} = require('child_process');

function exec(serviceName, command) {

    console.log(`Started service [${serviceName}]`);

    let cmd = spawn(command, [], {cwd: './' + serviceName, shell: true});

    cmd.stdout.on('data', function (data) {
        process.stdout.write(`[${serviceName}] ${data}`);
    });

    cmd.stderr.on('data', function (data) {
        process.stderr.write(`[${serviceName}] ${data}`);
    });

    return cmd;
}

const services = new Map();

services.set('externalservice1', exec('externalservice1', 'node src/server.js'));
services.set('externalservice2', exec('externalservice2', 'mvn spring-boot:run'));
services.set('server', exec('server', 'node src/server.js'));
services.set('worker', exec('worker', 'mvn spring-boot:run'));

process.on('SIGINT', async () => {
    for (var [name, cmd] of services) {
        console.log(`Killing service [${name}]`);
        cmd.stdin.pause();
        await cmd.kill();
    }
    process.exit();
});