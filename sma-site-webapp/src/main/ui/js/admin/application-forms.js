import {restClient} from '../rest/application-forms-rest-client.js';
import ApplicationFormsBuilder from '../application-forms-table/application-forms-builder.js';
import BootboxAlert from '../core/bootbox-alert.js';

let removeCallback = (item, event) => {
    console.log(item);
};

restClient.getAll(data => {
    if (data.type === 'error') {
        BootboxAlert.show(data);
    } else {
        let applicationForms = ApplicationFormsBuilder.build(data, removeCallback);
        document.getElementById('application-forms-table').appendChild(applicationForms);
    }
});