<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="ISO-8859-1" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Practice Database Administration</title>
<link href="./css/bootstrap.min.css" rel="stylesheet" />
<link href="./css/starter-template.css" rel="stylesheet" />
<script type="text/javascript">
	<jsp:include page="/js/jquery-3.2.1.js"/>
</script>
<script type="text/javascript">
	<jsp:include page="/js/bootstrap.min.js"/>
</script>
</head>

<body>
	<div id="queryModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">Custom query executor</h4>
				</div>

				<div class="modal-body">
					<form id="queryForm">
						<input type="text" class="form-control" id="queryField"
							name="query" />
					</form>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-success" data-dismiss="modal"
						id="executeButton">Execute</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid navbar-inverse">
		<div class="navbar-header navbar-inverse navbar-collapse">
			<a class="navbar-brand" href="#">Administration tool</a>
		</div>
		<div class="collapse navbar-collapse navbar-inverse">
			<ul class="nav navbar-nav">
			</ul>
		</div>
	</div>
	<div class="container-fluid" id="contentMain">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar" id="sidepanel">
				<ul class="nav nav-sidebar">
					<li><a href="#" id="createButton">Create</a></li>
					<li><a href="#">Delete</a></li>
					<li><a href="#" id="customQueryBttn">Custom query</a></li>
				</ul>
			</div>
			<h3 class="sub-header" id="secTitle">Content table</h3>
			<div class="table-responsive">
				<table class="table table-striped" id="contentTable"></table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		<jsp:include page="./js/methods.js"/>
	</script>
</body>
	</html>
</jsp:root>