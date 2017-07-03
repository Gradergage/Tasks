$(document).ready(function() {
	$("#customQueryBttn").click(function()
			{$("#queryModal").modal('show');
			});
	$(document).on("click", "#executeButton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
		var params = {query: $("#queryField").val()};
		$.post("QueryExecutor", $.param(params), function(responseText) {
			$("#contentTable").html(responseText); 
		});
    });
	
	/*$.get("QueryExecutor", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
    	$("#contentTable").html(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
    });
});*/
});