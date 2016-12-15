import GalleryItem from './gallery-item.js';
import GalleryRow from './gallery-row.js';

export default class GalleryBuilder {
    constructor(json) {
        this.items = [];
        for (let ref of json.data) {
            this.items.push(new GalleryItem(ref.id, ref.title, ref.src));
        }
    }

    build() {
        let rows = [];
        for (let i = 0; i < this.items.length; i++) {
            if (i % 3 == 0) {
                rows.push(new GalleryRow());
            }
            rows[rows.length - 1].append(this.items[i]);
        }

        let div = document.createElement('div');
        div.setAttribute('class', 'offset-md-2 col-md-8');
        for (let row of rows) {
            div.appendChild(row.build());
        }
        return div;
    }
}