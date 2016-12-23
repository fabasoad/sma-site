import GalleryItem from './../gallery-item.js';
import GalleryEditableControlPanel from './gallery-editable-control-panel.js';

export default class GalleryEditableItem extends GalleryItem {

    constructor(removeCallback) {
        super();
        this.removeCallback = removeCallback;
    }

    createGalleryControlPanel() {
        return new GalleryEditableControlPanel(this.removeCallback);
    }
}