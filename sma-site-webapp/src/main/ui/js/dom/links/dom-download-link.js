import DomLink from './dom-link.js';

export default class DomDownloadLink extends DomLink {

    constructor(href) {
        super(href, 'glyphicon-download');
    }
}