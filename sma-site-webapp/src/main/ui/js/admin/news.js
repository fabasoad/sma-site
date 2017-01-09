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
            restClient.create(obj, json => {
                BootboxAlert.show(json, refreshData);
            });
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