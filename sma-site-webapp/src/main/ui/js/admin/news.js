import {restClient} from '../rest/news-rest-client.js';
import NewsEditableBuilder from '../news/view/editable/news-editable-builder.js';
import NewsLoader from '../news/news-loader.js';
import Constants from '../core/constants.js';
import BootboxAlert from '../core/bootbox-alert.js';
import NewsDialogBox from '../news/news-dialog-box.js';

document.getElementById('news-confirm-button').addEventListener('click', event => {
    new NewsDialogBox({}).show({
        label: 'Create',
        callback: (obj, event) => {
            let result = true;

            let clearErrors = () => {
                $('div[id^="news-error-"]').html('');
                let labeledGroups = document.getElementsByClassName('news-labeled-group');
                for (let i = 0; i < labeledGroups.length; i++) {
                    labeledGroups.item(i).classList.remove('alert-error');
                }
            };

            clearErrors();

            restClient.create(obj, json => {
                if (json.type === 'validation-error') {
                    let findParent =
                        el => el.classList.contains('news-labeled-group') ? el : findParent(el.parentNode);

                    result = false;
                    for (let error of json.errors) {
                        let errorDiv = document.getElementById('news-error-' + error.id);
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
    new NewsDialogBox(item).show({
        label: 'Save',
        callback: (obj, event) => {
            restClient.update(item['id'], obj, json => {
                BootboxAlert.show(json, refreshData);
            });
        }
    });
};

let removeCallback = (item, event) => {
    bootbox.confirm({
        title: Constants.APPLICATION_NAME,
        message: "Do you really want to remove '" + item['title'] + "'?",
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
                restClient.delete(item['id'], data => {
                    BootboxAlert.show(data, refreshData);
                });
            }
        }
    });
};

let refreshData = () => NewsLoader.load(new NewsEditableBuilder(editCallback, removeCallback));

window.addEventListener('hashchange', refreshData);

refreshData();