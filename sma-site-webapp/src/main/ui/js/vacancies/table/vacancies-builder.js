import VacanciesRow from './vacancies-row.js';

export default class VacanciesBuilder {

    constructor(showDetailsCallback) {
        this.showDetailsCallback = showDetailsCallback;
    }

    createVacanciesRow(item) {
        return new VacanciesRow(item, this.showDetailsCallback);
    }

    build(json) {
        let tbody = document.createElement('tbody');
        for (let i = 0; i < json.data.length; i++) {
            tbody.appendChild(this.createVacanciesRow(json.data[i]).build(i + 1));
        }
        return tbody;
    }
}