import DomButton from './dom-button.js';

export default class DomRefreshButton extends DomButton {

    constructor(clickCallback, text) {
        super(clickCallback, 'glyphicon-refresh', text);
    }
}