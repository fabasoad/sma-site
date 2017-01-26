import NewsItem from './news-item.js';
import DomRefreshButton from '../../dom/buttons/dom-refresh-button.js';

export default class NewsBuilder {

    setSeeMoreCallback(seeMoreCallback) {
        this.seeMoreCallback = seeMoreCallback;
    }

    createNewsItem() {
        return new NewsItem();
    }

    /*
    <div class="col-md-12">
        <div>
            <NewsItem />
            <NewsItem />
            ...
        </div>
        <div class="text-center">
            <a href="#" id="loadmore" class="btn btn-primary">See more</a>
        </div>
    </div>
     */
    build(json, minimized) {
        let div = document.createElement('div');
        div.setAttribute('class', 'col-md-12');

        let divPosts = document.createElement('div');
        for (let item of json.data) {
            divPosts.appendChild(this.createNewsItem().build(item, minimized));
        }
        div.appendChild(divPosts);

        if (json['total-count'] > json.data.length) {
            let divTextCenter = document.createElement('div');
            divTextCenter.setAttribute('class', 'text-center');
            divTextCenter.appendChild(new DomRefreshButton(this.seeMoreCallback, 'See more'));

            div.appendChild(divTextCenter);
        }
        return div;
    }
}