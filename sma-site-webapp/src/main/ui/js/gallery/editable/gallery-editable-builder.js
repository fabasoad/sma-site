import GalleryBuilder from './../gallery-builder.js';
import GalleryEditableItem from './gallery-editable-item.js';

export default class GalleryEditableBuilder extends GalleryBuilder {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createGalleryItem() {
        return new GalleryEditableItem(this.editCallback, this.removeCallback);
    }
}