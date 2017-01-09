import NewsItem from './news-item.js';

export default class NewsBuilder {

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
            let text = document.createTextNode('See more');

            let a = document.createElement('a');
            a.setAttribute('href', '#');
            a.setAttribute('class', 'btn btn-primary');
            a.appendChild(text);

            let divTextCenter = document.createElement('div');
            divTextCenter.setAttribute('class', 'text-center');
            divTextCenter.appendChild(a);

            div.appendChild(divTextCenter);
        }
        return div;
    }
}