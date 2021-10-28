"use strict"

const grpc = require('grpc');
const wordServiceImpl = require('./word/wordServiceImpl.js');
const wordService = require('./word/wordService.js')

const HOST = process.env.HOST || "127.0.0.1"; // localhost
const PORT = process.env.PORT || "3001";

const server = new grpc.Server();

server.addService(wordService.service, {toUpperCaseWord: wordServiceImpl.toUpperCaseWord});

server.bind(`${HOST}:${PORT}`, grpc.ServerCredentials.createInsecure());

console.log(`gRPC server running at http://${HOST}:${PORT}`);

server.start();