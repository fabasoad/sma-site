import {restClient} from './rest/contacts-rest-client.js';

restClient.getAll(data => {
    let div = document.getElementById('contacts-container');

    let text = document.createTextNode(data.body);
    div.appendChild(text);
});