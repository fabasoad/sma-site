import {restClient} from '../rest/application-forms-rest-client.js';
import ApplicationFormsBuilder from '../application-forms-table/application-forms-builder.js';
import Constants from '../core/constants.js';
import BootboxAlert from '../core/bootbox-alert.js';

let removeCallback = (item, event) => {
    bootbox.confirm({
        title: Constants.APPLICATION_NAME,
        message: 'Do you really want to remove application form by "' + item['sender-name'] + '"?',
        buttons: {
            cancel: {
                label: 'No',
                className: 'btn-success'
            },
            confirm: {
                label: 'Yes',
                className: 'btn-danger'
            }
        },
        callback: result => {
            if (result) {
                restClient.delete(item['id'], data => {
                    BootboxAlert.show(data, refreshData);
                });
            }
        }
    });
};

let refreshData = () => {
    let table = document.getElementById('application-forms-table');
    table.classList.add('hide');
    restClient.getAll(json => {
        if (json.type === 'error') {
            BootboxAlert.show(json);
        } else {
            let element = table.getElementsByTagName("tbody");
            for (let i = element.length - 1; i >= 0; i--) {
                element[i].parentNode.removeChild(element[i]);
            }
            table.appendChild(ApplicationFormsBuilder.build(json, removeCallback));
            table.classList.remove('hide');
        }
    });
};

refreshData();