import DomLink from './dom-link.js';

export default class DomDownloadButton extends DomLink {

    constructor(href) {
        super(href, 'glyphicon-download');
    }
}