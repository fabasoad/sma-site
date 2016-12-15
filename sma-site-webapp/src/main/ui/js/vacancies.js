import {restClient} from './rest/vacancies-rest-client.js';
import VacanciesBuilder from './vacanies-table/vacancies-builder.js';

restClient.getAll(data => {
    let body = $('#vacancies-table').find('tbody');
    for (let td of VacanciesBuilder.build(data)) {
        body.append(td);
    }
});