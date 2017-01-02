import {restClient} from './rest/application-forms-rest-client.js';
import BootboxAlert from './core/bootbox-alert.js';

$("#application-form-upload").fileinput({
    uploadUrl: '/api/v1/application-forms',
    uploadAsync: false,
    maxFileCount: 1,
    elErrorContainer: "application-form-upload-error-block"
});

$("#application-form-upload").on('filebatchuploadsuccess', (event, json1) => {
    if (json1.response.type === 'error') {
        BootboxAlert.show(json1.response);
    } else {
        let senderName = document.getElementById('application-form-sender-name').value;
        if (senderName === '') {
            BootboxAlert.show({
                type: 'success',
                message: 'Application form uploaded successfully'
            });
        } else {
            restClient.update(json1.response.id, { 'sender-name' : senderName}, json2 => {
                BootboxAlert.show(json2);
            });
        }
    }
});