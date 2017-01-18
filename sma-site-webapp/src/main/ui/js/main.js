import {restClient} from './rest/main-rest-client.js';

restClient.getAll(data => {
    document.getElementById('main-container').innerHTML = data.body;
});