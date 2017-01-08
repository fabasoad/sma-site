import ReferenceItem from './reference-item.js';

export default class ReferencesBuilder {

    createReferenceItem() {
        return new ReferenceItem();
    }

    build(json) {
        let div = document.createElement('div');
        div.classList.add('row');
        for (let item of json.data) {
            div.appendChild(this.createReferenceItem().build(item));
        }
        return div;
    }
}