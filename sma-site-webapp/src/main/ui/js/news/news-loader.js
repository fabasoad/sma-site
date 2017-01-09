import {restClient} from '../rest/news-rest-client.js';

let loadInternal = (builder, restCall, wrapper = v => v) => {
    let div = document.getElementById('news-container');
    div.classList.add('hide');
    restCall(json => {
        while (div.firstChild) {
            div.removeChild(div.firstChild);
        }
        div.appendChild(builder.build(wrapper(json)));
        div.classList.remove('hide');
    });
};

export default class NewsLoader {

    static load(builder) {
        let hash = window.location.hash;
        if (hash) {
            loadInternal(builder, callback => restClient.getById(hash.slice(1), callback), i => new Object({ data: [ i ] }));
        } else {
            loadInternal(builder, restClient.getAll);
        }
    }
}