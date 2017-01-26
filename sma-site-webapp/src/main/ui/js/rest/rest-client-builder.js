import RestUtils from './rest-utils.js';

export default function RestClientBuilder(url) {
    let __url = '/api/v1/' + url;

    let methods = {
        getAll(callback) {
            $.ajax({
                type: 'GET',
                url: __url,
                complete: data => RestUtils.handleResponse(data, callback)
            });
        },
        getById(id, callback) {
            $.ajax({
                type: 'GET',
                url: __url + '/' + id,
                complete: data => RestUtils.handleResponse(data, callback)
            });
        },
        getLimit(limit, callback) {
            $.ajax({
                type: 'GET',
                url: __url + '?limit=' + limit,
                complete: data => RestUtils.handleResponse(data, callback)
            });
        },
        create(obj, callback) {
            try {
                this.validate(obj);
            } catch (e) {
                if (typeof callback === 'function') {
                    callback({
                        type: 'validation-error',
                        errors: e
                    });
                }
                return;
            }
            $.ajax({
                type: 'POST',
                url: __url,
                contentType: "application/json",
                dataType: 'json',
                data: obj,
                complete: data => RestUtils.handleResponse(data, callback)
            });
        },
        update(id, obj, callback) {
            try {
                this.validate(obj);
            } catch (e) {
                if (typeof callback === 'function') {
                    callback({
                        type: 'validation-error',
                        errors: e
                    });
                }
                return;
            }
            $.ajax({
                type: 'PUT',
                url: __url + '/' + (id || ''),
                contentType: "application/json",
                dataType: 'json',
                data: obj,
                complete: data => RestUtils.handleResponse(data, callback)
            });
        },
        delete(id, callback) {
            $.ajax({
                type: 'DELETE',
                url: __url + '/' + id,
                complete: data => RestUtils.handleResponse(data, callback)
            });
        },
        patch(obj, callback) {
            $.ajax({
                type: 'PATCH',
                url: __url,
                contentType: "application/json",
                dataType: 'json',
                data: obj,
                complete: data => RestUtils.handleResponse(data, callback)
            });
        }
    };

    let result = {
        validate: obj => {}
    };

    return {
        add(method) {
            if (typeof method === 'object') {
                Object.assign(result, method);
            } else if (methods.hasOwnProperty(method)) {
                Object.assign(result, { [method]: methods[method] });
            }
            return this;
        },
        build() {
            return result;
        }
    }
}