const {spawnSync} = require('child_process');

function exec(serviceName, command) {

    console.log(`Installing dependencies for [${serviceName}]`);
    console.log(`Folder: ${serviceName} Command: ${command}`);

    spawnSync(command, [], {
        cwd: serviceName,
        shell: true,
        stdio: 'inherit'
    });
}

exec('externalservice1', 'npm install');
exec('externalservice2', 'mvn clean install');
exec('server','npm install');
exec('worker', 'mvn clean install');
