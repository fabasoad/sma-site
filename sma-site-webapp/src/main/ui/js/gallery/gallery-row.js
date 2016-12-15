export default class GalleryRow {
    constructor() {
        this.items = [];
    }

    append(item) {
        this.items.push(item);
    }

    build() {
        let div = document.createElement('div');
        div.setAttribute('class', 'row');
        for (let item of this.items) {
            div.appendChild(item.build());
        }
        return div;
    }
}