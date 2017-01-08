import ReferencesBuilder from './../references-builder.js';
import ReferenceEditableItem from './reference-editable-item.js';

export default class ReferencesEditableBuilder extends ReferencesBuilder {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createReferenceItem() {
        return new ReferenceEditableItem(this.editCallback, this.removeCallback);
    }
}