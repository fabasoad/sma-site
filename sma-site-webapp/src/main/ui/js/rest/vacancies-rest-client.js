import {REST_CLIENT_RESOURCE, RestClientFactory} from './rest-client-factory.js';

let restClient = RestClientFactory.get(REST_CLIENT_RESOURCE.VACANCIES);
restClient.validate = obj => {
    let message = field => '"' + field + '" field is not valid';

    if (!obj['rank'] || obj['rank'] === '') {
        throw new Error(message('rank'));
    }
    if (!obj['joining-date'] || obj['joining-date'] === '' || !moment(obj['joining-date'], ['YYYY-MM-DD']).isValid()) {
        throw new Error(message('joining-date'));
    }
    if (!obj['contract-duration'] || obj['contract-duration'] === '') {
        throw new Error(message('contract-duration'));
    }
    if (!obj['nationality'] || obj['nationality'] === '') {
        throw new Error(message('nationality'));
    }
    if (!obj['wage'] || obj['wage'] === '') {
        throw new Error(message('wage'));
    }
    if (!obj['description'] || obj['description'] === '') {
        throw new Error(message('description'));
    }
};
export {restClient};