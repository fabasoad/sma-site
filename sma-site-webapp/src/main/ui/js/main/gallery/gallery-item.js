export default class GalleryItem {
    constructor(id, title, src) {
        this.id = id;
        this.title = title;
        this.src = src;
    }

    build() {
        let img = document.createElement('img');
        img.setAttribute('src', this.src);
        img.setAttribute('class', 'img-fluid');

        let a = document.createElement('a');
        a.setAttribute('href', this.src);
        a.setAttribute('data-title', this.title);
        a.setAttribute('data-toggle', 'lightbox');
        a.setAttribute('data-gallery', 'references-gallery');
        a.setAttribute('class', 'col-sm-4');
        a.appendChild(img);

        return a;
    }
}