import {restClient} from './../rest/contacts-rest-client.js';

restClient.getAll(data => {
    document.getElementById('editor').innerHTML = data.body;
});