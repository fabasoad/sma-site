import {restClient} from '../rest/vacancies-rest-client.js';

export default class VacanciesLoader {

    static load(builder) {
        let table = document.getElementById('vacancies-table');
        table.classList.add('hide');
        restClient.getAll(data => {
            let element = document.getElementsByTagName("tbody");
            for (let i = element.length - 1; i >= 0; i--) {
                element[i].parentNode.removeChild(element[i]);
            }

            table.appendChild(builder.build(data));
            table.classList.remove('hide');
        });
    }
}