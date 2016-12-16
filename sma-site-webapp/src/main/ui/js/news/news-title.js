export default class NewsTitle {
    // <div class="col-sm-9">
    //     <h3 class="pull-left">Welcome</h3>
    // </div>
    static build(title) {
        let text = document.createTextNode(title);

        let h3 = document.createElement('h3');
        h3.setAttribute('class', 'pull-left');
        h3.appendChild(text);

        let div = document.createElement('div');
        div.setAttribute('class', 'col-sm-9');
        div.appendChild(h3);

        return div;
    }
}