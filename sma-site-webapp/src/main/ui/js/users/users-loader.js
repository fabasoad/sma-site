import UsersBuilder from '../users/table/users-builder.js';
import {restClient} from '../rest/users-rest-client.js';

export default class UsersLoader {

    static load(editCallback, removeCallback) {
        let table = document.getElementById('users-table');
        table.classList.add('hide');
        restClient.getAll(data => {
            let element = table.getElementsByTagName("tbody");
            for (let i = element.length - 1; i >= 0; i--) {
                element[i].parentNode.removeChild(element[i]);
            }

            table.appendChild(new UsersBuilder(editCallback, removeCallback).build(data));
            table.classList.remove('hide');
        });
    }
}