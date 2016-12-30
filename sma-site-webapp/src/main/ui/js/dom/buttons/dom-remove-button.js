import DomButton from './dom-button.js';

export default class DomRemoveButton extends DomButton {

    constructor(clickCallback) {
        super(clickCallback, 'glyphicon-remove');
    }
}