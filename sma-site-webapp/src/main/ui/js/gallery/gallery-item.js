export default class GalleryItem {
    constructor(item) {
        this.item = item;
    }

    /*
    * <div class="col-lg-4 col-sm-6 col-xs-12">
    *   <a href="/public/img/references/ref_01.png">
    *       <img src="/public/img/references/ref_01.png" class="thumbnail img-responsive">
    *   </a>
    * </div>
    */
    build() {
        let img = document.createElement('img');
        img.setAttribute('src', this.item.src);
        img.classList.add('thumbnail');
        img.classList.add('img-responsive');

        let a = document.createElement('a');
        a.setAttribute('href', this.item.src);
        a.setAttribute('data-title', this.item.title);
        a.setAttribute('data-toggle', 'lightbox');
        a.setAttribute('data-gallery', 'sma-gallery');
        a.appendChild(img);

        let div = document.createElement('div');
        div.classList.add('col-lg-4');
        div.classList.add('col-sm-6');
        div.classList.add('col-xs-12');
        div.appendChild(a);

        return div;
    }
}