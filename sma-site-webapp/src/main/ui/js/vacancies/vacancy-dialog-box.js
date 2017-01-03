import Constants from '../core/constants.js';

let handleCallback = (callback, item, event) => {
    if (typeof callback === 'function') {
        let getVacancyValue =
            property => document.getElementById('vacancy-' + property).value || item[property];

        let properties = [
            'rank', 'vessel-type', 'joining-date', 'contract-duration', 'nationality', 'wage', 'description'
        ];
        let obj = {};
        for (let property of properties) {
            obj[property] = getVacancyValue(property);
        }

        callback(obj, event);
    }
};

export default class VacancyDialogBox {

    static show(item, confirmButton) {
        bootbox.dialog({
            title: Constants.APPLICATION_NAME,
            message: `
            <div class="form-group">
                <label for="vacancy-rank">Rank:</label>
                <input id="vacancy-rank" type="text" class="form-control" placeholder="` + (item['rank'] || '') + `"/>
            </div>
            <div class="form-group">
                <label for="vacancy-vessel-type">Vessel Type:</label>
                <input id="vacancy-vessel-type" type="text" class="form-control" placeholder="` + (item['vessel-type'] || '') + `"/>
            </div>
            <div class="raw">
                <div class="col-sm-3 form-group vacancy-form-group">
                    <label for="vacancy-joining-date">Joining Date:</label>
                    <div class="input-group date">
                        <input id="vacancy-joining-date" type="text" class="form-control" placeholder="` + (item['joining-date'] || '') + `"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>     
                <div class="col-sm-1"></div>
                <div class="col-sm-8 form-group vacancy-form-group">
                    <label for="vacancy-nationality">Nationality:</label>
                    <input id="vacancy-nationality" type="text" class="form-control" placeholder="` + (item['nationality'] || '') + `"/>
                </div>                   
            </div>
            <div class="raw">
                <div class="col-sm-5 form-group vacancy-form-group">
                    <label for="vacancy-contract-duration">Contract Duration:</label>
                    <input id="vacancy-contract-duration" type="text" class="form-control" placeholder="` + (item['contract-duration'] || '') + `"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-6 form
            <div class="form-group">-group vacancy-form-group">
                    <label for="vacancy-wage">Wage:</label>
                    <input id="vacancy-wage" type="text" class="form-control" placeholder="` + (item['wage'] || '') + `"/>
                </div>                   
            </div>
                <label for="vacancy-description">Description:</label>
                <textarea id="vacancy-description" rows="4" class="form-control" placeholder="` + (item['description'] || '') + `"/>
            </div>
            <script>
                $('#vacancy-joining-date').parent().datetimepicker({
                    ` + (item['joining-date'] ? 'defaultDate: "' + item['joining-date'] + '",' : '') + `
                    format: 'YYYY-MM-DD'
                });
            </script>
        `,
            buttons: {
                cancel: {
                    label: 'Cancel',
                    className: 'btn-default'
                },
                confirm: {
                    label: confirmButton.label,
                    className: 'btn-success',
                    callback: event => {
                        handleCallback(confirmButton.callback, item, event);
                    }
                }
            }
        });
    }
}