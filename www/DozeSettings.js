var exec = require('cordova/exec');

exports.isSupported = function(success, error) {
    exec(success, error, "DozeSettings", "isSupported", []);
};

exports.isWhitelisted = function(success, error) {
    exec(success, error, "DozeSettings", "isWhitelisted", []);
};

exports.openSettings = function(success, error) {
    exec(success, error, "DozeSettings", "openSettings", []);
};
