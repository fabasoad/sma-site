import BaseDialogBox from './base-dialog-box.js';

export default class ChangePasswordDialogBox extends BaseDialogBox {

    constructor() {
        super({
            id: 'change-password',
            message: `
                <div class="form-group change-password-labeled-group">
                    <label for="change-password-email">Email</label>
                    <div id="change-password-error-email" class="alert-danger"></div>
                    <input id="change-password-email" type="text" class="form-control" placeholder="Enter email"/>
                </div>
                <div class="form-group change-password-labeled-group">
                    <label for="change-password-old-password">Current password</label>
                    <div id="change-password-error-old-password" class="alert-danger"></div>
                    <input id="change-password-old-password" type="password" class="form-control" placeholder="Enter your current password"/>
                </div>
                <div class="form-group change-password-labeled-group">
                    <label for="change-password-new-password">New password</label>
                    <div id="change-password-error-new-password" class="alert-danger"></div>
                    <input id="change-password-new-password" type="password" class="form-control" placeholder="Enter new password"/>
                </div>
                <div class="form-group change-password-labeled-group">
                    <label for="change-password-repeated-password">Repeat new password</label>
                    <div id="change-password-error-repeated-password" class="alert-danger"></div>
                    <input id="change-password-repeated-password" type="password" class="form-control" placeholder="Enter new password again"/>
                </div>
            `,
            properties: ['email', 'old-password', 'new-password', 'repeated-password']
        });
    }

    getProperty(key) {
        return '';
    }
}