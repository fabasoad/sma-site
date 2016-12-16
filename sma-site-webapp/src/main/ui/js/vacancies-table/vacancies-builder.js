import VacanciesRow from './vacancies-row.js';

export default class VacanciesBuilder {
    static build(json) {
        let result = [];
        for (let i = 0; i < json.data.length; i++) {
            result.push(new VacanciesRow(json.data[i]).build(i + 1));
        }
        return result;
    }
}