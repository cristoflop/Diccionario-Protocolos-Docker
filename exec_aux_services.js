const {spawnSync} = require('child_process');

function exec(serviceName, command) {

    console.log(`Starting docker images for [${serviceName}]`);
    console.log(`Command: ${command}`);

    spawnSync(command, [], {

        shell: true,
        stdio: 'inherit'
    });
}

exec('DowmBrokerAndDbs', 'docker-compose -f docker/dictionary-stack.yaml down');
exec('BrokerAndDbs', 'docker-compose -f docker/dictionary-stack.yaml up -d --build');