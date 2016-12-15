import {REST_CLIENT_RESOURCE, RestClientFactory} from './rest-client-factory.js';

let restClient = RestClientFactory.get(REST_CLIENT_RESOURCE.APPLICATION_FORMS);
export {restClient};