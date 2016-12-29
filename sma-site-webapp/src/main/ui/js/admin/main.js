import {restClient} from '../rest/main-rest-client.js';
import BootboxAlert from '../core/bootbox-alert.js';

restClient.getAll(data => {
    document.getElementById('editor').innerHTML = data.body;
});

document.getElementById('wysiwyg-save-button').addEventListener('click', event => {
    restClient.update('SMA_MAIN_BODY', { body: document.getElementById('editor').innerHTML }, data => {
        BootboxAlert.show(data);
    });
});