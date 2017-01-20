import DomEditButton from '../../dom/buttons/dom-edit-button.js';
import DomRemoveButton from '../../dom/buttons/dom-remove-button.js';

export default class UsersRow {

    constructor(item, editCallback, removeCallback) {
        this.item = item;
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    build(index) {
        let createButton = (btnClazz, callback) => {
            let td = document.createElement('td');
            td.appendChild(new btnClazz(event => callback(this.item, event)));
            return td;
        };

        let tr = document.createElement('tr');

        let th = document.createElement('th');
        th.setAttribute('scope', 'row');
        th.innerHTML = index;
        tr.appendChild(th);

        let email = document.createElement('td');
        email.innerHTML = this.item['email'];
        tr.appendChild(email);

        tr.appendChild(createButton(DomEditButton, this.editCallback));
        tr.appendChild(createButton(DomRemoveButton, this.removeCallback));

        return tr;
    }
}
