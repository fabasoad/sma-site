import DomButton from './dom-button.js';

export default class DomEditButton extends DomButton {

    constructor(clickCallback) {
        super(clickCallback, 'glyphicon-pencil');
    }
}