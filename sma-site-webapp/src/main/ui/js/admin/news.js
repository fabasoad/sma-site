import {restClient} from './../rest/news-rest-client.js';
import NewsEditableBuilder from './../news/editable/news-editable-builder.js';
import Constants from './../core/constants.js';
import BootboxAlert from '../core/bootbox-alert.js';

document.getElementById('news-add-button').addEventListener('click', event => {
    BootboxAlert.show({
        type: 'success',
        message: 'Hello'
    });
});

let editCallback = (item, event) => {

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
                restClient.delete(item['id'], message => console.log(message));
            }
        }
    });
};

restClient.getAll(data => {
    let div = document.getElementById('news-container');
    div.appendChild(new NewsEditableBuilder(editCallback, removeCallback).build(data));
});