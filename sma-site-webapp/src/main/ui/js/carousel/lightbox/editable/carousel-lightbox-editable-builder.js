import CarouselLightboxBuilder from './../carousel-lightbox-builder.js';
import CarouselLightboxEditableItem from './carousel-lightbox-editable-item.js';

export default class CarouselLightboxEditableBuilder extends CarouselLightboxBuilder {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createCarouselLightboxItem() {
        return new CarouselLightboxEditableItem(this.editCallback, this.removeCallback);
    }
}