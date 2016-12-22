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
        let span = document.createElement('span');
        span.setAttribute('class', 'glyphicon glyphicon-plus');

        let button = document.createElement('button');
        button.setAttribute('type', 'button');
        button.setAttribute('class', 'btn btn-default btn-sm');
        button.addEventListener('click', this.addCallback);
        button.appendChild(span);

        let div = document.createElement('div');
        div.setAttribute('class', 'btn-group');
        div.appendChild(button);

        return div;
    }
}