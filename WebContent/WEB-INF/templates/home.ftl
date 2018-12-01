<!---------------------------------------------------------------
Project Name: Personal Music Library
Group #: 1
Contributor(s): Najee Searcy, Shawein Smith
Document Description: 
    This page serves as the index. It links to other features,
and provide the user with general information about the site.

Web Technologies: HTML, CSS, FreeMarker
----------------------------------------------------------------->
<HTML lang="en">
    <head>
        <title>Home</title>
        <link href="http://localhost:8080/MusicLibrary/resources/css/style.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/navbar.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/userBar.css" rel="stylesheet">
    </head>
    <body id="body">
        
        <!--This section holds the navigation bar-->
        <div class="navbar" id="inner">
            <div class="dropdown">
                <button  class="dropbtn">Home</button>
            </div>
            <div class="dropdown">
                <button onclick="location.href='http://localhost:8080/MusicLibrary/GenreServlet';"  class="dropbtn">Genres</button>
            </div>
            <div class="dropdown">
                <button onclick="location.href='http://localhost:8080/MusicLibrary/ArtistServlet';" class="dropbtn">Artists</button>
            </div>
            <div class="dropdown">
                <button onclick="location.href='http://localhost:8080/MusicLibrary/AlbumServlet';" class="dropbtn">Albums</button>
            </div>
            <div class="dropdown">
                <button onclick="location.href='http://localhost:8080/MusicLibrary/SongServlet';" class="dropbtn">Songs</button>
            </div> 
        </div>
        
        <div class="displayOptions">
        	Active User(s):
        	<#list activeUsers as user>
        		${user.userName}
        	</#list>
        	<form action="HomeServlet" method=POST class="form">
        		<input type=hidden name="option" value="logout">
        		<input type=hidden name="userName" value="${activeUser}">
        		<input type="submit" value="logout?">
        	</form>
        </div>
        
		<div id="welcome">
			Welcome ${activeUser}! 
		
			Here you can add, delete, and modify a collection your favorite artists, albums, and songs! You can also make comments about
			them to be seen by other users!
			
			<#if activeUser?matches("admin")>
			<div>Since you are an admin. You also have the ability to delete the comments of other users.</div>
			</#if>
		</div>
    </body>
</HTML>