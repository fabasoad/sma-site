import VacanciesRow from './vacancies-row.js';

export default class VacanciesBuilder {

    build(json) {
        let tbody = document.createElement('tbody');
        for (let i = 0; i < json.data.length; i++) {
            tbody.appendChild(new VacanciesRow(json.data[i]).build(i + 1));
        }
        return tbody;
    }
}