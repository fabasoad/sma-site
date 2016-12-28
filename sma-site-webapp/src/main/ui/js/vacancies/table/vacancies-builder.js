import VacanciesRow from './vacancies-row.js';

export default class VacanciesBuilder {

    createVacanciesRow(item) {
        return new VacanciesRow(item);
    }

    build(json) {
        let tbody = document.createElement('tbody');
        for (let i = 0; i < json.data.length; i++) {
            tbody.appendChild(this.createVacanciesRow(json.data[i]).build(i + 1));
        }
        return tbody;
    }
}