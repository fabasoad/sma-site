import VacanciesBuilder from '../vacancies-builder.js';
import VacanciesEditableRow from './vacancies-editable-row.js';

export default class VacanciesEditableBuilder extends VacanciesBuilder {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createVacanciesRow(item) {
        return new VacanciesEditableRow(item, this.editCallback, this.removeCallback);
    }
}
