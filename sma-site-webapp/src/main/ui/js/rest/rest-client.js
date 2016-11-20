import $ from 'jquery';

export default function RestClient() {
    return {
        getAll(callback) {
            $.ajax({
                type: 'GET',
                url: this.__url,
                success: data => {
                    if (typeof callback === 'function') {
                        callback(data);
                    }
                }
            });
        },
        getById(id, callback) {
            $.ajax({
                type: 'GET',
                url: this.__url + '/' + id,
                success: data => {
                    if (typeof callback === 'function') {
                        callback(data);
                    }
                }
            });
        },
        create(obj, callback) {
            $.ajax({
                type: 'POST',
                url: this.__url,
                dataType: 'json',
                data: obj,
                success: data => {
                    if (typeof callback === 'function') {
                        callback(data);
                    }
                }
            });
        },
        update(id, obj, callback) {
            $.ajax({
                type: 'PUT',
                url: this.__url + '/' + id,
                dataType: 'json',
                data: obj,
                success: data => {
                    if (typeof callback === 'function') {
                        callback(data);
                    }
                }
            });
        },
        delete(id, callback) {
            $.ajax({
                type: 'DELETE',
                url: this.__url + '/' + id,
                success: data => {
                    if (typeof callback === 'function') {
                        callback(data);
                    }
                }
            });
        }
    }
}

Object.defineProperty(RestClient, '__url', {
    set: val => 'api/v1/' + val
});