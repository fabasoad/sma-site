import BaseDialogBox from '../core/base-dialog-box.js';

export default class UserDialogBox extends BaseDialogBox {

    constructor(item, roles) {
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
                <script>
                    var userRolesSelect = document.getElementById('user-role-name');
                    ` + roles.map(roleItem => `var userRoleOption` +  roleItem['id'] + ` = document.createElement("option");
                                userRoleOption` +  roleItem['id'] + `.setAttribute("id", "role-` +  roleItem['id'] + `");
                                userRoleOption` +  roleItem['id'] + `.appendChild(document.createTextNode("` + roleItem['role-name'] + `"));
                                if (` + (roleItem['role-name'] === item['role-name']) + `) {
                                    userRoleOption` +  roleItem['id'] + `.setAttribute("selected", "true");
                                }
                                userRolesSelect.appendChild(userRoleOption` +  roleItem['id'] + `);`).join('') + `
                </script>
            `,
            properties: ['email', 'role-name']
        });
    }
}