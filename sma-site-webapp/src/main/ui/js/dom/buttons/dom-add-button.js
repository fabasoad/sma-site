import DomButton from './dom-button.js';

export default class DomAddButton extends DomButton {

    constructor(clickCallback) {
        super(clickCallback, 'glyphicon-plus');
    }
}