import BaseDialogBox from '../core/base-dialog-box.js';

export default class UserDialogBox extends BaseDialogBox {

    constructor(item) {
        super({
            id: 'user',
            message: `
                <div class="form-group user-labeled-group">
                    <label for="user-email">Email</label>
                    <div id="user-error-email" class="alert-danger"></div>
                    <input id="user-email" type="text" class="form-control" value="` + (item['email'] || '') + `" placeholder="` + (item['email'] || '') + `"/>
                </div>
            `,
            properties: ['email']
        });
        this.item = item;
    }

    getProperty(key) {
        return this.item[key] || '';
    }
}