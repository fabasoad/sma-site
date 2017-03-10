import CarouselLightboxItem from './../carousel-lightbox-item.js';
import CarouselLightboxEditableControlPanel from './carousel-lightbox-editable-control-panel.js';

export default class CarouselLightboxEditableItem extends CarouselLightboxItem {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createCarouselLightboxControlPanel() {
        return new CarouselLightboxEditableControlPanel(this.editCallback, this.removeCallback);
    }
}