export default class DomLink {

    constructor(href, className) {
        let a = document.createElement('a');
        a.classList.add('btn');
        a.classList.add('btn-default');
        a.classList.add('btn-sm');
        a.setAttribute('target', '_blank');
        a.setAttribute('href', href);

        let span = document.createElement('span');
        span.classList.add('glyphicon');
        span.classList.add(className);
        a.appendChild(span);

        return a;
    }
}