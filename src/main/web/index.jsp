<html>
	<head>
		<title>Welcome</title>
	</head>
	<body>
		<h1>Hello World.</h1>

        		<p>
				<%
        			out.println("Sum of 10 and 20 = ");
        			out.print(10 + 20);
        		%>
        		</p>

        		<p>
        		Also say <a href="hello">hello</a>!
        		</p>

        		<hr>

        		<p>

                <table border=1>
                <tr>
                    <td> session </td><td> <%= session %> </td>
                </tr>
                <tr>
                    <td> username </td><td> <%= session.getAttribute("username") %> </td>
                </tr>
                </table>

        		</p>

	</body>
</html>