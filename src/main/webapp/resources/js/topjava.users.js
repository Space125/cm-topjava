let ctx;

// $(document).ready(function () {
$(function () {
    // https://stackoverflow.com/a/5064235/548473
    ctx = {
        ajaxUrl: "admin/users/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        }),
        updateTable: function () {
            $.get(ctx.ajaxUrl, updateTableWithData)
        }
    };
    makeEditable();
});

//https://jquery-docs.ru/is/
//https://jquery-docs.ru/closest/
function enable(checked, id) {
    let enable = checked.is(":checked")

    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl + id,
        data: "enabled=" + enable
    }).done(function () {
        checked.closest("tr").attr("data-UserEnabled", enable)
        successNoty(enable ? "Enabled" : "Disabled")
    })
}