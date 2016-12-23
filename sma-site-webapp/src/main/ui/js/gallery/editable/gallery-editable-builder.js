import GalleryBuilder from './../gallery-builder.js';
import GalleryEditableItem from './gallery-editable-item.js';

export default class GalleryEditableBuilder extends GalleryBuilder {

    constructor(removeCallback) {
        super();
        this.removeCallback = removeCallback;
    }

    createGalleryItem() {
        return new GalleryEditableItem(this.removeCallback);
    }
}