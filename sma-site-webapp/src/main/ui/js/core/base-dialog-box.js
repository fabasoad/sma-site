import Constants from '../core/constants.js';

export default class BaseDialogBox {

    constructor(config) {
        this.config = config;
    }

    getProperty(key) {
    }

    getDocumentValue(key) {
        return document.getElementById(this.config.id + '-' + key).value;
    }

    show(confirmButton) {
        let config = {
            title: Constants.APPLICATION_NAME,
            onEscape: true,
            message: this.config.message,
            buttons: {
                cancel: {
                    label: 'Cancel',
                    className: 'btn-default'
                },
                confirm: {
                    label: confirmButton.label,
                    className: 'btn-success',
                    callback: event => {
                        let result = true;
                        if (typeof confirmButton.callback === 'function') {
                            let getValue =
                                key => this.getDocumentValue(key) || this.getProperty(key);

                            let obj = {};
                            for (let property of this.config.properties) {
                                obj[property] = getValue(property);
                            }

                            result = confirmButton.callback(obj, event);
                        }
                        return result;
                    }
                }
            }
        };
        if (this.config.size) {
            config.size = this.config.size;
        }
        bootbox.dialog(config);
    }
}