export default class NewsCreationDate {

    divClass() {
        return 'col-sm-3';
    }

    // <div class="col-sm-3">
    //  <h4 class="pull-right">
    //   <small><em>2014-07-30 18:30:00</em></small>
    //  </h4>
    // </div>
    build(creationDate) {
        let text = document.createTextNode(creationDate);

        let em = document.createElement('em');
        em.appendChild(text);

        let small = document.createElement('small');
        small.appendChild(em);

        let h4 = document.createElement('h4');
        h4.setAttribute('class', 'pull-right');
        h4.appendChild(small);

        let div = document.createElement('div');
        div.setAttribute('class', this.divClass());
        div.appendChild(h4);

        return div;
    }
}