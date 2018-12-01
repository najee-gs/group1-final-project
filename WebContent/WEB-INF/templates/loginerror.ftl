<!---------------------------------------------------------------
Project Name: Personal Music Library
Group #: 1
Contributor(s): Najee Searcy, Shawein Smith
Document Description: 
    This page displays when the user enters the wrong username and password combination.
    It also provides a registration feature.
    
Web Technologies: HTML, CSS
----------------------------------------------------------------->
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="http://localhost:8080/MusicLibrary/resources/css/LoginStyle.css">
  <script src="http://localhost:8080/MusicLibrary/resources/scripts/logInScript.js"></script>
  <title>Welcome!</title>
</head>

<body id="mainBody" background="resources/imgs/background2.jpg" >
<button onclick="original()">Original</button>
<button onclick="greyScale()">GreyScale</button>
<button onclick="colorfull()">Colorful</button>
  <div class="main">
    <p class="sign" align="center">Invalid Login:</p>
    <form class="form1" action="login.html">
      <input type="submit" class="submit" value="Return">
      <br>
      <br>
    </form>
    <form class="form1" action=HomeServlet method=POST>
      <input type=hidden name="option" value="register">
      <input id="user" class="username " type="text" name="userName" placeholder="New Username">
      <input class="pass" type="password"  name="Password" placeholder="New Password">
      <input type="submit" class="submit" value="Register">
    </form>  
</body>

</html>