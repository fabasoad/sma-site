import {restClient} from '../rest/news-rest-client.js';

export default class NewsLoader {

    static load(builder) {
        restClient.getAll(data => {
            let div = document.getElementById('news-container');
            div.appendChild(builder.build(data));
        });
    }
}