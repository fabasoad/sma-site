export default function RestClientBuilder(url) {
    let __url = '/api/v1/' + url;

    let methods = {
        getAll(callback) {
            $.ajax({
                type: 'GET',
                url: __url,
                complete: data => {
                    if (typeof callback === 'function') {
                        callback(JSON.parse(data.responseText));
                    }
                }
            });
        },
        getById(id, callback) {
            $.ajax({
                type: 'GET',
                url: __url + '/' + id,
                complete: data => {
                    if (typeof callback === 'function') {
                        callback(JSON.parse(data.responseText));
                    }
                }
            });
        },
        create(obj, callback) {
            $.ajax({
                type: 'POST',
                url: __url,
                contentType: "application/json",
                dataType: 'json',
                data: obj,
                complete: data => {
                    if (typeof callback === 'function') {
                        callback(JSON.parse(data.responseText));
                    }
                }
            });
        },
        update(id, obj, callback) {
            $.ajax({
                type: 'PUT',
                url: __url + '/' + id,
                contentType: "application/json",
                dataType: 'json',
                data: obj,
                complete: data => {
                    if (typeof callback === 'function') {
                        callback(JSON.parse(data.responseText));
                    }
                }
            });
        },
        delete(id, callback) {
            $.ajax({
                type: 'DELETE',
                url: __url + '/' + id,
                complete: data => {
                    if (typeof callback === 'function') {
                        callback(JSON.parse(data.responseText));
                    }
                }
            });
        }
    };

    let result = {};

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