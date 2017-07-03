$(document).ready(function() {
	$("#customQueryBttn").click(function()
			{$("#queryModal").modal('show');
			});
	/* Used to link with servlet via ajax and replace Content Table inner HTML*/
	$(document).on("click", "#executeButton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
		var params = {query: $("#queryField").val()};
		$.post("QueryExecutor", $.param(params), function(responseText) {
			$("#contentTable").html(responseText); 
		});
    });
});