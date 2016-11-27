export default function RestClientBuilder(url) {
    let __url = '/api/v1/' + url;

    let methods = {
        getAll(callback) {
            $.ajax({
                type: 'GET',
                url: __url,
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
                url: __url + '/' + id,
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
                url: __url,
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
                url: __url + '/' + id,
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
                url: __url + '/' + id,
                success: data => {
                    if (typeof callback === 'function') {
                        callback(data);
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
            } else {
                Object.assign(result, { [method]: methods[method] });
            }
            return this;
        },
        build() {
            return result;
        }
    }
}