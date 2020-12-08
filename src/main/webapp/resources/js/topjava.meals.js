var ctx, mealAjaxUrl = "profile/meals/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

$(function () {
    ctx = {
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            return formatDate(date);
                        }
                        return date;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderEditBtn
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                $(row).attr("data-mealExcess", data.excess)
            }
        }),
        updateTable: updateFilteredTable
    };
    makeEditable();
});

//https://xdsoft.net/jqplugins/datetimepicker/
//Range between date#
let startDate = $('#startDate')
let endDate = $('#endDate')
startDate.datetimepicker({
    timepicker: false,
    format: "Y-m-d",
    onShow: function () {
        this.setOptions({
            maxDate: endDate.val() || false
        })
    }
})

endDate.datetimepicker({
    timepicker: false,
    format: "Y-m-d",
    onShow: function () {
        this.setOptions({
            minDate: startDate.val() || false
        })
    }
})

let startTime = $('#startTime')
let endTime = $('#endTime')
startTime.datetimepicker({
    datepicker: false,
    format: "H:i",
    onShow: function () {
        this.setOptions({
            maxTime: endTime.val() || false
        })
    }
})

endTime.datetimepicker({
    datepicker: false,
    format: "H:i",
    onShow: function () {
        this.setOptions({
            minTime: startTime.val() || false
        })
    }
})

$('#dateTime').datetimepicker({
    format: "Y-m-d H:i"
})