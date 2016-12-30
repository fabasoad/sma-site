import DomEditButton from '../../dom/buttons/dom-edit-button.js';
import DomRemoveButton from '../../dom/buttons/dom-remove-button.js';

export default class GalleryEditableControlPanel {

    constructor(editCallback, removeCallback) {
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    /*
    * <div class="btn-group gallery-editable-control-panel">
    *   <button type="button" class="btn btn-default btn-sm">
    *     <span class="glyphicon glyphicon-pencil"></span>
    *   </button>
    *   <button type="button" class="btn btn-default btn-sm">
    *     <span class="glyphicon glyphicon-remove"></span>
    *   </button>
    * </div>
    */
    build(item) {
        let div = document.createElement('div');
        div.classList.add('btn-group');
        div.classList.add('gallery-editable-control-panel');
        div.appendChild(new DomEditButton(event => this.editCallback(item, event)));
        div.appendChild(new DomRemoveButton(event => this.removeCallback(item, event)));

        return div;
    }
}