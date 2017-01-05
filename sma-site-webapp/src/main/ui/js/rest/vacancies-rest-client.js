import {REST_CLIENT_RESOURCE, RestClientFactory} from './rest-client-factory.js';

let restClient = RestClientFactory.get(REST_CLIENT_RESOURCE.VACANCIES);
restClient.validate = obj => {
    let label = id => $("label[for='vacancy-" + id + "']").text();
    let notEmptyMessage = id => label(id) + ' cannot be empty';

    let errors = [];

    if (!obj['rank'] || obj['rank'] === '') {
        errors.push({
            id: 'rank',
            message: notEmptyMessage('rank')
        });
    }
    if (!obj['joining-date'] || obj['joining-date'] === '') {
        errors.push({
            id: 'joining-date',
            message: notEmptyMessage('joining-date')
        });
    } else {
        let joiningDate = moment(obj['joining-date'], ['YYYY-MM-DD']);
        if (!joiningDate.isValid()) {
            errors.push({
                id: 'joining-date',
                message: label('joining-date') + ' is invalid. Valid format is "YYYY-MM-DD"'
            });
        } else if (joiningDate < moment()) {
            errors.push({
                id: 'joining-date',
                message: label('joining-date') + " cannot be less than today's date"
            });
        }
    }
    if (!obj['contract-duration'] || obj['contract-duration'] === '') {
        errors.push({
            id: 'contract-duration',
            message: notEmptyMessage('contract-duration')
        });
    }
    if (!obj['nationality'] || obj['nationality'] === '') {
        errors.push({
            id: 'nationality',
            message: notEmptyMessage('nationality')
        });
    }
    if (!obj['wage'] || obj['wage'] === '') {
        errors.push({
            id: 'wage',
            message: notEmptyMessage('wage')
        });
    }
    if (!obj['description'] || obj['description'] === '') {
        errors.push({
            id: 'description',
            message: notEmptyMessage('description')
        });
    }
    if (errors.length > 0) {
        throw errors;
    }
};
export {restClient};