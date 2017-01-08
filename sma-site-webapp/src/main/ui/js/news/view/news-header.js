import NewsCreationDate from './news-creation-date.js';
import NewsTitle from './news-title.js';

export default class NewsHeader {

    createNewsTitle() {
        return new NewsTitle();
    }

    createNewsCreationDate() {
        return new NewsCreationDate();
    }

    createNewsControlPanel() {
    }

    // <div class="panel-heading">
    //     <div class="text-center">
    //         <div class="row">
    //             <NewsTitle />
    //             <NewsCreationDate />
    //         </div>
    //     </div>
    // </div>
    build(item) {
        let divRow = document.createElement('div');
        divRow.setAttribute('class', 'row');
        divRow.appendChild(this.createNewsTitle().build(item['title']));
        divRow.appendChild(this.createNewsCreationDate().build(item['creation-date']));

        let controlPanel = this.createNewsControlPanel();
        if (controlPanel) {
            divRow.appendChild(controlPanel.build(item));
        }

        let divTextCenter = document.createElement('div');
        divTextCenter.setAttribute('class', 'text-center');
        divTextCenter.appendChild(divRow);

        let divPanelHeading = document.createElement('div');
        divPanelHeading.setAttribute('class', 'panel-heading');
        divPanelHeading.appendChild(divTextCenter);

        return divPanelHeading;
    }
}