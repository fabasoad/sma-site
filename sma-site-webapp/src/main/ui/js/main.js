import {restClient} from './rest/main-rest-client.js';

restClient.getAll(data => {
    let div = document.getElementById('main-container');

    let text = document.createTextNode(data.body);
    div.appendChild(text);
});