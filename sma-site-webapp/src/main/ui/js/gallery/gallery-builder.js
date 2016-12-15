import GalleryItem from './gallery-item.js';
import GalleryRow from './gallery-row.js';

export default class GalleryBuilder {
    static build(json) {
        let rows = [];
        for (let i = 0; i < json.data.length; i++) {
            if (i % 3 == 0) {
                rows.push(new GalleryRow());
            }
            rows[rows.length - 1].append(new GalleryItem(json.data[i].id, json.data[i].title, json.data[i].src));
        }

        let div = document.createElement('div');
        div.setAttribute('class', 'offset-md-2 col-md-8');
        for (let row of rows) {
            div.appendChild(row.build());
        }
        return div;
    }
}