import CarouselLightboxItem from './carousel-lightbox-item.js';

export default class CarouselLightboxBuilder {

    createCarouselLightboxItem() {
        return new CarouselLightboxItem();
    }

    build(json) {
        let div = document.createElement('div');
        div.classList.add('row');
        for (let item of json.data) {
            div.appendChild(this.createCarouselLightboxItem().build(item));
        }
        return div;
    }
}