import {REST_CLIENT_RESOURCE, RestClientFactory} from './rest-client-factory.js';

let restClient = RestClientFactory.get(REST_CLIENT_RESOURCE.REFERENCES);
restClient.getAll(data => {
    alert(JSON.stringify(data));
});