import NewsCreationDate from './news-creation-date.js';
import NewsTitle from './news-title.js';

export default class NewsHeader {
    // <div class="panel-heading">
    //     <div class="text-center">
    //         <div class="row">
    //             <NewsTitle />
    //             <NewsCreationDate />
    //         </div>
    //     </div>
    // </div>
    static build(title, creationDate) {
        let divRow = document.createElement('div');
        divRow.setAttribute('class', 'row');
        divRow.appendChild(NewsTitle.build(title));
        divRow.appendChild(NewsCreationDate.build(creationDate));

        let divTextCenter = document.createElement('div');
        divTextCenter.setAttribute('class', 'text-center');
        divTextCenter.appendChild(divRow);

        let divPanelHeading = document.createElement('div');
        divPanelHeading.setAttribute('class', 'panel-heading');
        divPanelHeading.appendChild(divTextCenter);

        return divPanelHeading;
    }
}