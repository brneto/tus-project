$(document).ready(function () {
    $("#hseListTable").DataTable({
        "lengthMenu": [
            [5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
        ],
        "ordering": false,
        stateSave: true
    });
    $("#gpListTable").DataTable({
        "lengthMenu": [
            [5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
        ],
        "ordering": false,
        stateSave: true
    });
    $("#pharmaListTable").DataTable({
        "lengthMenu": [
            [5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
        ],
        "ordering": false,
        stateSave: true
    });
    $("#patientListTable").DataTable({
        "lengthMenu": [
            [5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
        ],
        "ordering": false,
        stateSave: true
    });
    $("#osmListTable").DataTable({
        "lengthMenu": [
            [5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
        ],
        "ordering": false,
        stateSave: true
    });
    $("#prescriptionListTable").DataTable({
        "lengthMenu": [
            [5, 10, 15, 20, -1], [5, 10, 15, 20, "All"]
        ],
        "ordering": false,
        stateSave: true
    });
});


