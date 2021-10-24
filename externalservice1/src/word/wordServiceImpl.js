"use strict"

function toUpperCaseWord(call, callback) {

    console.log('Request received: ' + JSON.stringify(call));

    const {word} = call.request;
    const uppercase = word.toUpperCase();
    const defer = 1000 + Math.random() * 2000;

    setTimeout(() => {
        callback(null, {uppercase});
    }, defer);

}

module.exports = {toUpperCaseWord}