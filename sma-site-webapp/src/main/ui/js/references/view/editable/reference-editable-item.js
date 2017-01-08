import ReferenceItem from './../reference-item.js';
import ReferencesEditableControlPanel from './references-editable-control-panel.js';

export default class ReferenceEditableItem extends ReferenceItem {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createReferencesControlPanel() {
        return new ReferencesEditableControlPanel(this.editCallback, this.removeCallback);
    }
}