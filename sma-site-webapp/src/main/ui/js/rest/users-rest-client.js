import {REST_CLIENT_RESOURCE, RestClientFactory} from './rest-client-factory.js';

let restClient = RestClientFactory.get(REST_CLIENT_RESOURCE.USERS);
restClient.validate = obj => {
    let label = id => $("label[for='user-" + id + "']").text();

    let errors = [];

    if (!obj['email'] || obj['email'] === '') {
        errors.push({
            id: 'email',
            message: label('email') + ' cannot be empty'
        });
    }
    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(obj['email'])) {
        errors.push({
            id: 'email',
            message: label('email') + ' is invalid'
        });
    }
    if (errors.length > 0) {
        throw errors;
    }
};
export {restClient};