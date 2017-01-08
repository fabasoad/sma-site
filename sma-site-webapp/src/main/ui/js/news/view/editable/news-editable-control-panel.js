import DomEditButton from '../../../dom/buttons/dom-edit-button.js';
import DomRemoveButton from '../../../dom/buttons/dom-remove-button.js';

export default class NewsEditableControlPanel {

    constructor(editCallback, removeCallback) {
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    // <div class="col-sm-2 pull-right">
    //   <button type="button" class="btn btn-default btn-sm">
    //     <span class="glyphicon glyphicon-pencil"></span>
    //   </button>
    //   <button type="button" class="btn btn-default btn-sm">
    //     <span class="glyphicon glyphicon-remove"></span>
    //   </button>
    // </div>
    build(item) {
        let div = document.createElement('div');
        div.setAttribute('class', 'col-sm-2 pull-right');
        div.appendChild(new DomEditButton(event => this.editCallback(item, event)));
        div.appendChild(new DomRemoveButton(event => this.removeCallback(item, event)));
        return div;
    }
}
