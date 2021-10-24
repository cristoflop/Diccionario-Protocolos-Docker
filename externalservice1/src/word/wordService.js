"use strict"

const grpc = require('grpc');
const protoLoader = require('@grpc/proto-loader');

const packageDefinition = protoLoader.loadSync(__dirname + '/../../WordService.proto',
    {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
    });

const wordServiceProto = grpc.loadPackageDefinition(packageDefinition);

module.exports = wordServiceProto.es.urjc.cloudapps.worker.grpc.WordService;