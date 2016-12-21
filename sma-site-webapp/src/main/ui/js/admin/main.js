import {restClient} from './../rest/main-rest-client.js';

restClient.getAll(data => {
    document.getElementById('editor').innerHTML = data.body;
});