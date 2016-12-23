import GalleryItem from './gallery-item.js';

export default class GalleryBuilder {
    static build(json) {
        let div = document.createElement('div');
        div.classList.add('row');
        for (let item of json.data) {
            div.appendChild(new GalleryItem(item).build());
        }
        return div;
    }
}