import GalleryItem from './gallery-item.js';

export default class GalleryBuilder {

    createGalleryItem() {
        return new GalleryItem();
    }

    build(json) {
        let div = document.createElement('div');
        div.classList.add('row');
        for (let item of json.data) {
            div.appendChild(this.createGalleryItem().build(item));
        }
        return div;
    }
}