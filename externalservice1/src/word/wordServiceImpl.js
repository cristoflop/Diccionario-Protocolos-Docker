"use strict"

function toUpperCaseWord(call, callback) {

    console.log('Request received: ' + JSON.stringify(call.request));

    const word = call.request.word;
    const uppercase = word.toUpperCase();
    const defer = 1000 + Math.random() * 2000;

    const response = {word: uppercase};

    setTimeout(() => {
        callback(null, response);
    }, defer);

}

module.exports = {toUpperCaseWord}