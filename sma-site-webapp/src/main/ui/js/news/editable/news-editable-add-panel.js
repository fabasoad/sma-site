import DomAddButton from '../../dom/buttons/dom-add-button.js';

export default class NewsEditableAddPanel {

    constructor(addCallback) {
        this.addCallback = addCallback;
    }
    /**
     * <div class="btn-group">
     *   <button type="button" class="btn btn-default btn-sm">
     *     <span class="glyphicon glyphicon-plus"></span>
     *   </button>
     * </div>
     */
    build() {
        let div = document.createElement('div');
        div.setAttribute('class', 'btn-group');
        div.appendChild(new DomAddButton(this.addCallback));

        return div;
    }
}