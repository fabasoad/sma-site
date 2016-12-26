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
        let editButton = document.createElement('button');
        editButton.classList.add('btn');
        editButton.classList.add('btn-default');
        editButton.classList.add('btn-sm');
        editButton.addEventListener('click', event => this.editCallback(item, event));

        let editSpan = document.createElement('span');
        editSpan.classList.add('glyphicon');
        editSpan.classList.add('glyphicon-pencil');
        editButton.appendChild(editSpan);

        let removeButton = document.createElement('button');
        removeButton.classList.add('btn');
        removeButton.classList.add('btn-default');
        removeButton.classList.add('btn-sm');
        removeButton.addEventListener('click', event => this.removeCallback(item, event));

        let removeSpan = document.createElement('span');
        removeSpan.classList.add('glyphicon');
        removeSpan.classList.add('glyphicon-remove');
        removeButton.appendChild(removeSpan);

        let div = document.createElement('div');
        div.classList.add('btn-group');
        div.classList.add('gallery-editable-control-panel');
        div.appendChild(editButton);
        div.appendChild(removeButton);

        return div;
    }
}