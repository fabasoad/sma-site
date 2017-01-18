import {restClient} from './rest/contacts-rest-client.js';

restClient.getAll(data => {
    document.getElementById('contacts-container').innerHTML = data.body;
});