import {REST_CLIENT_RESOURCE, RestClientFactory} from './rest-client-factory.js';

let restClient = RestClientFactory.get(REST_CLIENT_RESOURCE.MAIN);
restClient.validate = obj => {
    let errors = [];

    if (!obj['body'] || obj['body'] === '') {
        errors.push({
            id: 'body',
            message: 'Body cannot be empty'
        });
    }

    if (errors.length > 0) {
        throw errors;
    }
};
export {restClient};