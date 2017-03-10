import CarouselViewBuilder from './view/carousel-view-builder.js';
import {restClient} from '../rest/carousel-images-rest-client.js';

export default class CarouselLoader {

    static loadView(callback) {
        restClient.getAll(json => {
            if (json['total-count'] > 0) {
                CarouselViewBuilder.build(json.data);
            }
            if (callback && typeof callback === 'function') {
                callback(json);
            }
        });
    }

    static loadLightbox(builder) {
        let gallery = document.getElementById('carousel-gallery');
        gallery.classList.add('hide');
        restClient.getAll(json => {
            while (gallery.firstChild) {
                gallery.removeChild(gallery.firstChild);
            }
            gallery.appendChild(builder.build(json));
            gallery.classList.remove('hide');
        });
    }
}