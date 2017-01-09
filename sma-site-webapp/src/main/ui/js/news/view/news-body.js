const MAX_TEXT_LENGTH = 250;

export default class NewsBody {
    // <div class="panel-body">
    //     Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
    //     tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
    //     quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
    //     consequat. Duis aute irure dolor in... <a href="/news/2">Read more</a>
    // </div>
    constructor(id, body) {
        let div = document.createElement('div');
        div.setAttribute('class', 'panel-body');
        div.innerHTML = (body.length > MAX_TEXT_LENGTH ? body.substr(0, MAX_TEXT_LENGTH) + '...' : body) + ' ';

        let readMoreText = document.createTextNode('Read more');

        let a = document.createElement('a');
        a.setAttribute('href', '/news#' + id);
        a.appendChild(readMoreText);

        div.appendChild(a);

        return div;
    }
}