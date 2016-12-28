import VacanciesRow from '../vacancies-row.js';
import DomEditButton from '../../../dom/dom-edit-button.js';
import DomRemoveButton from '../../../dom/dom-remove-button.js';

export default class VacanciesEditableRow extends VacanciesRow {

    constructor(item, editCallback, removeCallback) {
        super(item);
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createEditColumn() {
        let td = document.createElement('td');
        td.appendChild(new DomEditButton(event => this.editCallback(this.vacancy, event)));
        return td;
    }

    createRemoveColumn() {
        let td = document.createElement('td');
        td.appendChild(new DomRemoveButton(event => this.removeCallback(this.vacancy, event)));
        return td;
    }

    buildRank() {
        return document.createTextNode(this.vacancy['rank']);
    }
}