import {restClient} from '../rest/contacts-rest-client.js';
import BootboxAlert from '../core/bootbox-alert.js';

restClient.getAll(data => {
    document.getElementById('editor').innerHTML = data.body;
});

document.getElementById('wysiwyg-save-button').addEventListener('click', event => {
    restClient.update(null, { body: document.getElementById('editor').innerHTML }, data => {
        BootboxAlert.show(data);
    });
});