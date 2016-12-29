export default function RestClientBuilder(url) {
    let __url = '/api/v1/' + url;

    let handleResponse = (data, callback) => {
        if (typeof callback === 'function') {
            let json;
            try {
                json = JSON.parse(data.responseText);
            } catch (e) {
                json = {
                    type: 'error',
                    message: data.responseText,
                    status: data.status
                };
            }
            callback(json);
        }
    };

    let methods = {
        getAll(callback) {
            $.ajax({
                type: 'GET',
                url: __url,
                complete: data => handleResponse(data, callback)
            });
        },
        getById(id, callback) {
            $.ajax({
                type: 'GET',
                url: __url + '/' + id,
                complete: data => handleResponse(data, callback)
            });
        },
        create(obj, callback) {
            $.ajax({
                type: 'POST',
                url: __url,
                contentType: "application/json",
                dataType: 'json',
                data: obj,
                complete: data => handleResponse(data, callback)
            });
        },
        update(id, obj, callback) {
            $.ajax({
                type: 'PUT',
                url: __url + '/' + id,
                contentType: "application/json",
                dataType: 'json',
                data: obj,
                complete: data => handleResponse(data, callback)
            });
        },
        delete(id, callback) {
            $.ajax({
                type: 'DELETE',
                url: __url + '/' + id,
                complete: data => handleResponse(data, callback)
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