import {restClient} from './rest/main-rest-client.js';

restClient.getAll(data => {
    let div = document.getElementById('main-container');
    div.innerHTML = data.body;
});