import {restClient} from './rest/vacancies-rest-client.js';
import VacanciesBuilder from './vacancies-table/vacancies-builder.js';

restClient.getAll(data => {
    let body = document.getElementById('vacancies-table').getElementsByTagName('tbody').item(0);
    for (let td of VacanciesBuilder.build(data)) {
        body.append(td);
    }
});