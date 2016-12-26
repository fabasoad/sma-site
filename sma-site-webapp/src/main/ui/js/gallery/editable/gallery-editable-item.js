import GalleryItem from './../gallery-item.js';
import GalleryEditableControlPanel from './gallery-editable-control-panel.js';

export default class GalleryEditableItem extends GalleryItem {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createGalleryControlPanel() {
        return new GalleryEditableControlPanel(this.editCallback, this.removeCallback);
    }
}