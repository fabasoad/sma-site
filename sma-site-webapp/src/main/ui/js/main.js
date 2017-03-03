import {restClient} from './rest/params-rest-client.js';
import Constants from './core/constants.js';

restClient.getById(Constants.PARAMS.MAIN, data => {
    document.getElementById('main-container').innerHTML = data.body;
});