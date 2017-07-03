$(document).ready(function() {
	$("#customQueryBttn").click(function()
			{$("#queryModal").modal('show');
			});
	$("#executeButton").click(function()
			{
		var oracleQuery=$("#queryField").text();
		$.ajax({
			type: "GET",
			url: "QueryExecutor",
			dataType: "html",
			data: query,
			success: function (data) {
				alert(data);
			},
			error: function (request, status, error) {
				alert(error);
			}});});
});