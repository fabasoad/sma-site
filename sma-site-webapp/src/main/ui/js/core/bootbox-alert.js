import Constants from './constants.js';

export default class BootboxAlert {

    static show(data, callback) {
        let config = {
            message: data.message || 'Error occurs during contacts saving.' + (data.status ? ' Status code: ' + data.status : ''),
            title: Constants.APPLICATION_NAME
        };
        if (data.type === 'error') {
            config.className = 'alert-error';
        } else if (typeof callback === 'function') {
            config.callback = callback;
        }
        bootbox.alert(config);
    }
}