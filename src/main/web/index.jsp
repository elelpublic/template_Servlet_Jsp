<html>
	<head>
		<title>Welcome</title>
	</head>
	<body>
		<h1>Hello World. (JSP)</h1>

        		<p>
				<%
        			out.println("Sum of 10 and 20 = ");
        			out.print(10 + 20);
        		%>
        		</p>

        		<p>
        		Also say hello to the <a href="hello">servlet</a>!
        		</p>

        		<hr>

        		<p>

                <table border=1>
                <tr>
                    <td> session </td><td> <%= session %> </td>
                </tr>
                <tr>
                    <td> value </td><td> <%= session.getAttribute("value") %> </td>
                </tr>
                </table>

        		</p>

	</body>
</html>