import BaseDialogBox from '../core/base-dialog-box.js';
import {restClient} from '../rest/user-roles-rest-client.js';

export default class UserDialogBox extends BaseDialogBox {

    constructor(item) {
        super(item, {
            id: 'user',
            message: `
                <div class="form-group user-labeled-group">
                    <label for="user-email">Email</label>
                    <div id="user-error-email" class="alert-danger"></div>
                    <input id="user-email" type="text" class="form-control" value="` + (item['email'] || '') + `" placeholder="` + (item['email'] || '') + `"/>
                </div>
                <div class="form-group user-labeled-group">
                    <label for="user-role-name">Role</label>
                    <div id="user-error-role-name" class="alert-danger"></div>
                    <select id="user-role-name" class="form-control"></select>
                </div>
            `,
            properties: ['email', 'role-name']
        });
    }

    postInit() {
        restClient.getAll(json => {
            let select = document.getElementById('user-role-name');
            for (let roleItem of json.data) {
                let option = document.createElement('option');
                option.setAttribute('id', 'role-' + roleItem['id']);
                option.appendChild(document.createTextNode(roleItem['role-name']));
                if (roleItem['role-name'] === this.item['role-name']) {
                    option.setAttribute('selected', 'true');
                }
                select.appendChild(option);
            }
        });
    }
}