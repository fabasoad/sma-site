export default class NewsEditableControlPanel {

    constructor(editCallback, removeCallback) {
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    // <div class="col-sm-2">
    //   <button type="button" class="btn btn-default btn-sm">
    //     <span class="glyphicon glyphicon-pencil"></span>
    //   </button>
    //   <button type="button" class="btn btn-default btn-sm">
    //     <span class="glyphicon glyphicon-remove"></span>
    //   </button>
    // </div>
    build(item) {
        let div = document.createElement('div');
        div.setAttribute('class', 'col-sm-2');

        let editSpan = document.createElement('span');
        editSpan.setAttribute('class', 'glyphicon glyphicon-pencil');

        let editButton = document.createElement('button');
        editButton.setAttribute('type', 'button');
        editButton.setAttribute('class', 'btn btn-default btn-sm pull-right');
        editButton.appendChild(editSpan);
        editButton.addEventListener('click', event => this.editCallback(item, event));
        div.appendChild(editButton);

        let removeSpan = document.createElement('span');
        removeSpan.setAttribute('class', 'glyphicon glyphicon-remove');

        let removeButton = document.createElement('button');
        removeButton.setAttribute('type', 'button');
        removeButton.setAttribute('class', 'btn btn-default btn-sm pull-right');
        removeButton.appendChild(removeSpan);
        removeButton.addEventListener('click', event => this.removeCallback(item, event));
        div.appendChild(removeButton);

        return div;
    }
}
