import BootboxAlert from '../core/bootbox-alert.js';
import Constants from '../core/constants.js';
import UserDialogBox from '../users/user-dialog-box.js';
import UsersLoader from '../users/users-loader.js';
import {restClient} from '../rest/users-rest-client.js';

document.getElementById('users-confirm-button').addEventListener('click', event => {
    new UserDialogBox({}).show({
        label: 'Create',
        callback: (obj, event) => {
            let result = true;

            let clearErrors = () => {
                $('div[id^="user-error-"]').html('');
                let labeledGroups = document.getElementsByClassName('user-labeled-group');
                for (let i = 0; i < labeledGroups.length; i++) {
                    labeledGroups.item(i).classList.remove('alert-error');
                }
            };

            clearErrors();

            restClient.create(obj, json => {
                if (json.type === 'validation-error') {
                    let findParent =
                        el => el.classList.contains('user-labeled-group') ? el : findParent(el.parentNode);

                    result = false;
                    for (let error of json.errors) {
                        let errorDiv = document.getElementById('user-error-' + error.id);
                        errorDiv.innerHTML = error.message;
                        findParent(errorDiv.parentNode).classList.add('alert-error');
                    }
                } else {
                    BootboxAlert.show(json, refreshData);
                }
            });
            return result;
        }
    });
});

let editCallback = (item, event) => {
    new UserDialogBox(item).show({
        label: 'Save',
        callback: (obj, event) => {
            let result = true;

            let clearErrors = () => {
                $('div[id^="user-error-"]').html('');
                let labeledGroups = document.getElementsByClassName('user-labeled-group');
                for (let i = 0; i < labeledGroups.length; i++) {
                    labeledGroups.item(i).classList.remove('alert-error');
                }
            };

            clearErrors();

            restClient.update(item['id'], obj, json => {
                if (json.type === 'validation-error') {
                    let findParent =
                        el => el.classList.contains('user-labeled-group') ? el : findParent(el.parentNode);

                    result = false;
                    for (let error of json.errors) {
                        let errorDiv = document.getElementById('user-error-' + error.id);
                        errorDiv.innerHTML = error.message;
                        findParent(errorDiv.parentNode).classList.add('alert-error');
                    }
                } else {
                    BootboxAlert.show(json, refreshData);
                }
            });
            return result;
        }
    });
};

let removeCallback = (item, event) => {
    bootbox.confirm({
        title: Constants.APPLICATION_NAME,
        message: 'Do you really want to remove <b>' + item['email'] + '</b> user?',
        buttons: {
            cancel: {
                label: 'No',
                className: 'btn-success'
            },
            confirm: {
                label: 'Yes',
                className: 'btn-danger'
            }
        },
        callback: result => {
            if (result) {
                restClient.delete(item['id'], json => {
                    BootboxAlert.show(json, refreshData);
                });
            }
        }
    });
};

let refreshData = () => UsersLoader.load(editCallback, removeCallback);
refreshData();