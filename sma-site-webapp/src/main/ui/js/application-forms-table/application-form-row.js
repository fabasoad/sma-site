import DomDownloadLink from '../dom/links/dom-download-link.js';
import DomRemoveButton from '../dom/buttons/dom-remove-button.js';

export default class ApplicationFormRow {

    constructor(item, removeClickCallback) {
        this.item = item;
        this.removeClickCallback = removeClickCallback;
    }

    build(index) {
        let tr = document.createElement('tr');

        let th = document.createElement('th');
        th.classList.add('col-md-1');
        th.setAttribute('scope', 'row');
        th.innerHTML = index;
        tr.appendChild(th);

        let senderNameColumn = document.createElement('td');
        senderNameColumn.classList.add('col-md-9');
        senderNameColumn.innerHTML = this.item['sender-name'];
        tr.appendChild(senderNameColumn);

        let downloadColumn = document.createElement('td');
        downloadColumn.classList.add('col-md-1');
        downloadColumn.appendChild(new DomDownloadLink(this.item['file-name']));
        tr.appendChild(downloadColumn);

        let removeColumn = document.createElement('td');
        removeColumn.classList.add('col-md-1');
        removeColumn.appendChild(new DomRemoveButton(event => this.removeClickCallback(this.item, event)));
        tr.appendChild(removeColumn);

        return tr;
    }
}