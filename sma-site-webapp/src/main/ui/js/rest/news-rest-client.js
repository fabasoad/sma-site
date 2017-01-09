import {REST_CLIENT_RESOURCE, RestClientFactory} from './rest-client-factory.js';

let restClient = RestClientFactory.get(REST_CLIENT_RESOURCE.NEWS);
restClient.validate = obj => {
    let label = id => $("label[for='news-" + id + "']").text();
    let notEmptyMessage = id => label(id) + ' cannot be empty';

    let keys = [ 'title', 'body' ];
    let errors = [];

    for (let key of keys) {
        if (!obj[key] || obj[key] === '') {
            errors.push({
                id: key,
                message: notEmptyMessage(key)
            });
        }
    }

    if (errors.length > 0) {
        throw errors;
    }
};
export {restClient};