import {restClient} from '../rest/vacancies-rest-client.js';

export default class VacanciesLoader {

    static load(builder) {
        restClient.getAll(data => {
            let table = document.getElementById('vacancies-table');
            table.appendChild(builder.build(data));
            table.classList.remove('hide');
        });
    }
}