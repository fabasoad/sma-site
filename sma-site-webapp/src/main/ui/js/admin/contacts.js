import {restClient} from '../rest/params-rest-client.js';
import Constants from '../core/constants.js';
import BootboxAlert from '../core/bootbox-alert.js';

restClient.getById(Constants.PARAMS.CONTACTS, data => {
    document.getElementById('editor').innerHTML = data.body;
});

document.getElementById('wysiwyg-save-button').addEventListener('click', event => {
    restClient.update(Constants.PARAMS.CONTACTS, { body: document.getElementById('editor').innerHTML }, data => {
        BootboxAlert.show(data);
    });
});