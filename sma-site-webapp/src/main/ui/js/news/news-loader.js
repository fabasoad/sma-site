import {restClient} from '../rest/news-rest-client.js';

let loadInternal = (builder, restCall, minimized = true, wrapper = v => v) => {
    let div = document.getElementById('news-container');
    div.classList.add('hide');
    restCall(json => {
        while (div.firstChild) {
            div.removeChild(div.firstChild);
        }
        if (json.type === 'error') {
            let divMd12 = document.createElement('div');
            divMd12.classList.add('col-md-12');
            divMd12.appendChild(document.createTextNode(json.message));
            div.appendChild(divMd12);
        } else {
            div.appendChild(builder.build(wrapper(json), minimized));
        }
        div.classList.remove('hide');
    });
};

export default class NewsLoader {

    static load(builder) {
        let hash = window.location.hash;
        if (hash) {
            loadInternal(builder, callback => restClient.getById(hash.slice(1), callback), false, i => new Object({ data: [ i ] }));
        } else {
            loadInternal(builder, restClient.getAll);
        }
    }
}