import VacanciesRow from '../vacancies-row.js';
import DomEditButton from '../../../dom/buttons/dom-edit-button.js';
import DomRemoveButton from '../../../dom/buttons/dom-remove-button.js';

export default class VacanciesEditableRow extends VacanciesRow {

    constructor(item, editCallback, removeCallback) {
        super(item);
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createEditColumn() {
        let td = document.createElement('td');
        td.appendChild(new DomEditButton(event => this.editCallback(this.item, event)));
        return td;
    }

    createRemoveColumn() {
        let td = document.createElement('td');
        td.appendChild(new DomRemoveButton(event => this.removeCallback(this.item, event)));
        return td;
    }

    buildRank() {
        return document.createTextNode(this.item['rank']);
    }
}