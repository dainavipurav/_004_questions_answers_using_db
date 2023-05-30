<!DOCTYPE html>
<html>
<style>
input[type=submit], a {
	width: 100%;
	background-color: #4CAF50;
	font-size: 16px;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	align-content: center;
	border-radius: 4px;
	cursor: pointer;
}

input[type=file] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=submit]:hover {
	background-color: #45a049;
}

div {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
	align-items: center;
	align-content: center;
}
</style>
<body>

	<h3>Please click the Start Test button</h3>

	<div>
		<form method="post" action="QAServlet">
			<input type="submit" value="Start Test" />
		</form>


	</div>

</body>
</html>

