export default class DomButton {

    constructor(clickCallback, className, text) {
        let button = document.createElement('button');
        button.classList.add('btn');
        button.classList.add('btn-default');
        button.classList.add('btn-sm');
        button.addEventListener('click', clickCallback);

        let span = document.createElement('span');
        span.classList.add('glyphicon');
        span.classList.add(className);
        button.appendChild(span);

        if (text) {
            button.appendChild(document.createTextNode(' ' + text));
        }

        return button;
    }
}