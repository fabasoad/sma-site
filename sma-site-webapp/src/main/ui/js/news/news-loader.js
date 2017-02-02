import {restClient} from '../rest/news-rest-client.js';

let divError404 = document.getElementsByClassName('error-404').item(0);

let loadInternal = (builder, restCall, minimized = true, wrapper = v => v) => {
    let div = document.getElementById('news-container');
    div.classList.add('hide');
    restCall(json => {
        while (div.firstChild) {
            div.removeChild(div.firstChild);
        }
        if (json.type === 'error') {
            if (json.status == 404) {
                divError404.classList.remove('hide');
            } else {
                let divMd12 = document.createElement('div');
                divMd12.classList.add('col-md-12');
                divMd12.appendChild(document.createTextNode(json.message));
                div.appendChild(divMd12);
            }
        } else {
            div.appendChild(builder.build(wrapper(json), minimized));
        }
        div.classList.remove('hide');
    });
};

export default class NewsLoader {

    constructor() {
        this.limit = 10;
    }

    __loadInternal(builder) {
        divError404.classList.add('hide');
        let hash = window.location.hash;
        if (hash) {
            loadInternal(
                builder,
                callback => restClient.getById(hash.slice(1), callback),
                false,
                i => new Object({ data: [ i ] })
            );
        } else {
            loadInternal(builder, callback => restClient.getLimit(this.limit, callback));
        }
    }

    load(builder) {
        builder.setSeeMoreCallback(event => {
            this.limit += 2;
            this.__loadInternal(builder);
        });
        this.__loadInternal(builder);
    }
}