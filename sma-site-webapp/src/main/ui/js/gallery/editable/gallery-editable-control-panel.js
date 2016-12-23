export default class GalleryEditableControlPanel {

    constructor(removeCallback) {
        this.removeCallback = removeCallback;
    }

    /*
    * <div class="btn-group gallery-editable-control-panel">
    *   <button type="button" class="btn btn-default btn-sm" style="position: absolute; bottom: 30px; right: 30px;">
    *     <span class="glyphicon glyphicon-remove"></span>
    *   </button>
    * </div>
    */
    build(item) {
        let button = document.createElement('button');
        button.classList.add('btn');
        button.classList.add('btn-default');
        button.classList.add('btn-sm');
        button.addEventListener('click', event => this.removeCallback(item, event));

        let span = document.createElement('span');
        span.classList.add('glyphicon');
        span.classList.add('glyphicon-remove');
        button.appendChild(span);

        let div = document.createElement('div');
        div.classList.add('btn-group');
        div.classList.add('gallery-editable-control-panel');
        div.appendChild(button);

        return div;
    }
}