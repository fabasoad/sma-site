import {restClient} from './rest/params-rest-client.js';
import Constants from './core/constants.js';

restClient.getById(Constants.PARAMS.CONTACTS, data => {
    document.getElementById('contacts-container').innerHTML = data.body;
});