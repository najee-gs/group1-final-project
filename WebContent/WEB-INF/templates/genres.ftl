<!---------------------------------------------------------------
Project Name: Personal Music Library
Group #: 1
Contributor(s): Najee Searcy
Document Description:
    This page displays genre data. It retrieves the data from
the GenresServlet.

Web Technologies: HTML, CSS, Javascript
----------------------------------------------------------------->
<html lang="en">
<head>
		<meta charset="ISO-8859-1">
		<title>Genres</title>
        <link href="http://localhost:8080/MusicLibrary/resources/css/style.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/navbar.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/userBar.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/genreEffects.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/insertForm.css" rel="stylesheet">
        <script src="http://localhost:8080/MusicLibrary/resources/scripts/script.js"></script>
        <script type="text/javascript" src="http://localhost:8080/MusicLibrary/resources/scripts/jquery224.min.js"></script>
        <script type="text/javascript" language="javascript">
        $(document).ready(function() {	
	    	$("#delButton").click(function() {
	        	var deleteGrid = `
	        		        <div class="row">
	        	<#list genres as genre>
	        		<form action=GenreServlet method=POST>
	        			<input type=hidden name="option" value="deletion">
	        			<input type=hidden name="genreID" value="${genre.id}">
			            <div class="column">
		                <input type="image" src="${genre.imagePath}" class="delgenreImage zoom" style ="width:100%">
		            	</div>
	            	</form>
	        	</#list>
	        </div>
	        	`;
	        	
	        	var contentDiv = document.getElementById("content");
	        
	    		if (contentDiv.firstElementChild.className == "row") {
					 $("#content").html(deleteGrid);
	    		}
	    	});
	    });
        </script>
</head>
<body>

        <!--This section holds the navigation bar-->
        <div class="navbar" id="inner">
            <div class="dropdown">
                <a class="dropbtn" href="http://localhost:8080/MusicLibrary/HomeServlet">Home</a>
            </div>
            <div class="dropdown">
                <button class="dropbtn">Genres</button>
                <div class="dropdown-content">
                    <a onclick="openForm()">Add</a>
                    <a id="delButton">Delete</a>
                </div>
            </div>
            <div class="dropdown">
                <a class="dropbtn" href="http://localhost:8080/MusicLibrary/ArtistServlet">Artists</a>
            </div>
            <div class="dropdown">
                <a class="dropbtn" href="http://localhost:8080/MusicLibrary/AlbumServlet">Albums</a>
            </div>
            <div class="dropdown">
                <a class="dropbtn" href="http://localhost:8080/MusicLibrary/SongServlet">Songs</a>
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
		
       <div id="content">
	        <!--This section holds artist images in a grid-->
	        <div class="row">
	        	<#list genres as genre>
		            <div class="column">
	                <img src="${genre.imagePath}" class="genreImage zoom" style ="width:100%"  onclick="on('${genre.id}-overlay')">
	            	</div>
	        	</#list>
	        </div>
	   </div>
	   
	   <#list genres as genre>
          <div class="overlay" id="${genre.id}-overlay">
          		<div id="otext">
	          		<h2>${genre.name} Albums: ${genre.albumCount}</h2>
	          		<h2>${genre.name} Singles: ${genre.singlesCount}</h2>
          			<button type="submit" class="btn cancel" onclick="off('${genre.id}-overlay')">Close</button>
          		</div>
   		  </div>
	   </#list>
	
		<!--
		<h1>Genre TABLE:</h1>

		<table bgcolor="white" border="1" CELLPADDING=0 CELLSPACING=0 WIDTH=%100>
		<tr><th>ID</th><th>Genre Name</th><th>Albums Stored</th><th>Singles Stored</th></tr><tr
		<#list genres as genre>
			<tr><td>${genre.id}</td><td>${genre.name}</td><td>${genre.albumCount}</td><td>${genre.singlesCount}</td></tr>
		</#list>
		</table>
		-->

	       <div class="form-popup" id="myForm">
          <form action="GenreServlet" method="POST" class="form-container">
          	<input type=hidden name="option" value="insert">
            <h1>Add Genre</h1>

            <label for="GenreName"><b>Genre Name:</b></label>
            <input type="text" placeholder="Genre Name" name="name" required>
            
            <label for="ImagePath"><b>Image Path:</b></label>
            <input type="text" placeholder="(...).jpg" name="imagePath" required>


            <button type="submit" class="btn">Add Genre</button>
            <button type="submit" class="btn cancel" onclick="closeForm()">Close</button>
          </form>
</body>
</html>