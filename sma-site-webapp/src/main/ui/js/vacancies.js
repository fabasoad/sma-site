import {restClient} from './rest/vacancies-rest-client.js';
import VacanciesBuilder from './vacancies-table/vacancies-builder.js';

restClient.getAll(data => {
    let table = document.getElementById('vacancies-table');
    let body = table.getElementsByTagName('tbody').item(0);
    for (let td of VacanciesBuilder.build(data)) {
        body.append(td);
    }
    table.classList.remove('hide');
});