<!---------------------------------------------------------------
Project Name: Personal Music Library
Group #: 1
Contributor(s): Najee Searcy
Document Description:
    This page displays song data. It retrieves the data from
the SongsServlet. 

Web Technologies: HTML, CSS, Javascript, JQuery/Ajax
----------------------------------------------------------------->
<HTML lang="en">
    <head>
        <title>Songs</title>
        <link href="http://localhost:8080/MusicLibrary/resources/css/style.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/navbar.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/userBar.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/insertForm.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/songsPage.css" rel="stylesheet">
        <script src="http://localhost:8080/MusicLibrary/resources/scripts/script.js"></script>
        <script type="text/javascript" src="http://localhost:8080/MusicLibrary/resources/scripts/jquery224.min.js"></script>
        <script type="text/javascript" language="javascript">
            $(document).ready(function() {
            	
                $("#t2 tr:gt(0)").click(function() {
                    var audioLoc = $(this).find("a").attr("data-value");
                    var local = $(this).find("x").attr("data-value");
                   	
                    if(local == '1') {
	                    if(audioLoc) {
	                        $("#audioSource").attr("src", audioLoc);
	                        $("#audio")[0].load();
	                        $("#audio")[0].play();
	                        $("#YT").removeClass("show");
                        	$("#audio").addClass("show");
	                    }
	                    else {
	                        alert("Error: Invalid Selection.");
	                    }
                    } else {
                    		$("#audio").removeClass("show");
                    		$("#audio")[0].pause();
                    		$("#YT").addClass("show");
                    		$("#YT").attr("src", audioLoc);
                    }
                });
                
            	$("#delButton").click(function() {
            		var tableFormView = `
              			 <div>
				            <table id="tblDEL" style="width:100%">
				                <tr>
				                    <th>Title</th>
				                    <th>Artist(s)</th>
				                    <th>Local</th>
				                    <th></th>
				                </tr>
				                <#list songs as song>
				                <tr>
				                    <td>${song.name}</td>
				                    <td>${song.artist}</td>
				                    <td><x type=hidden data-value="${song.local}"></x>${song.local}</td>
				                    <td><a data-value="${song.path}"></a></td>
				                    <td>
				                    <form id="${song.id}" action=SongServlet method=POST>
						            	<input type=hidden name="songID" value="${song.id}">
						            	<input type=hidden name="option" value="delete">
						            	<input type=submit value="delete">
				                    </form>
				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
          
            		
            		var contentDiv = document.getElementById("content");
					$("#content").html(tableFormView);
            		
            	});
            	
            	$("#modButton").click(function() {
	        		var modifyTV = `
              			 <div>
				            <table id="tblMOD" style="width:100%">
				                <tr>
				                    <th>Title</th>
				                    <th>Artist(s)</th>
				                    <th>Local</th>
				                    <th></th>
				                </tr>
				                <#list songs as song>
				                <tr>
				                    <td>${song.name}</td>
				                    <td>${song.artist}</td>
				                    <td><x type=hidden data-value="${song.local}"></x>${song.local}</td>
				                    <td><a data-value="${song.path}"></a></td>
				                    <td><button type="button" onclick="on('${song.id}mod-overlay')">Modify</button></td>
				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
            		
            		var contentDiv = document.getElementById("content");
					$("#content").html(modifyTV);
            	});
            	
            	<#list songs as song>
            	$("#changeSongName${song.id}").click(function() {
            		var miniForm = "<td><input type='text' name='songTitle'></td>";
        			$("#nameVal${song.id}").html(miniForm);
            	});
            	
            	$("#changeArtist${song.id}").click(function() {
            		var miniForm = `	                
		            		<td>
		            		<input type="text" list="artistName" name="artistName"  placeholder="Artist Name"  required/>
			                <datalist id="artistName">
			                <#list artists as artist>
			                	<option value="${artist.id}">${artist.name}</option>
			                </#list>
			                </datalist>
			                <input type=hidden name="artist" value="">
			                </td>
			                `;
        			$("#artistVal${song.id}").html(miniForm);
            	});
            	
            	$("#changeSongPath${song.id}").click(function() {
            		var miniForm = "<td><input type='text' name='songPath'></td>";
        			$("#songPathVal${song.id}").html(miniForm);
            	});
            	
            	$("#changeSongLocState${song.id}").click(function() {
            		var miniForm = `
            				<td>				
            				<select name="local">
							<option value="false">0</option>
							<option value="true">1</option>
							</select>
							</td>`;
        			$("#songLocVal${song.id}").html(miniForm);
            	});
            	</#list>
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
                <a class="dropbtn" href="http://localhost:8080/MusicLibrary/GenreServlet">Genres</a>
            </div>
            <div class="dropdown">
                <a class="dropbtn" href="http://localhost:8080/MusicLibrary/ArtistServlet">Artists</a>
            </div>
            <div class="dropdown">
                <a class="dropbtn" href="http://localhost:8080/MusicLibrary/AlbumServlet">Albums</a>
            </div>
            <div class="dropdown">
                <button class="dropbtn">Songs</button>
                <div class="dropdown-content">
                    <a onclick="openForm()">Add</a>
                    <a id="delButton">Delete</a>
                    <a id="modButton">Modify</a>
                </div>
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
        
        <div id="Player">
        	<iframe id="YT" class="hide" width="640" height="360" src="" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
	        <audio id="audio" class="hide" controls="controls">
            <source id="audioSource" src="" type="audio/mp3">
            	Your browser does not support the audio format.
        	</audio>
        </div>
        
        
        <!--This section holds a table of songs-->
        <div id="content">
	        <div style="padding:10px;">
	            <table id="t2" style="width:100%">
	                <tr>
	                    <th>Title</th>
	                    <th>Artist(s)</th>
	                    <th>Local</th>
	                    <th></th>
	                </tr>
	                <#list songs as song>
	                <tr>
	                    <td>${song.name}</td>
	                    <td>${song.artist}</td>
	                    <td><x type=hidden data-value="${song.local}"></x>${song.local}</td>
	                    <td><a data-value="${song.path}"></a></td>
	                </tr>
	                </#list>
	            </table>
	        </div>
        </div>
        
        <!-- This section hold the modification forms -->
        <#list songs as song>
        <div class="overlay" id="${song.id}mod-overlay" >
            <div id="otext">
            	<form action="SongServlet" method="POST">
		            <table bgcolor="white" id="t2" style="width:100%">
		            <input type="hidden" name="option" value="modify">
		            <input type="hidden" name="songID" value="${song.id}">
		            	<tr>
		            		<th>Field</th>
			            	<th>Current Value</th>
			            	<th>New Value</th>
		            	</tr>
		            	<tr>
		            		<td>Name</td>
		            		<td>${song.name}</td>
		            		<td id="nameVal${song.id}"><button type="button" id="changeSongName${song.id}">Change Value</button></td>
		            	</tr>
		            	<tr>
		                    <td>Song Artist</td>
		                    <td>${song.artist}</td>
		                    <td id="artistVal${song.id}"><button type="button" id="changeArtist${song.id}">Change Value</button></td>
		            	</tr>
		            	
		            	<tr>
		            		<td>Song Path</td>
		            		<td>${song.path}</td>
		            		<td id="songPathVal${song.id}"><button type="button" id="changeSongPath${song.id}">Change Value</button></td>
		            	</tr>
		            	
		            	<tr>
		            		<td>Local</td>
		            		<td>${song.local}</td>
		            		<td id="songLocVal${song.id}"><button type="button" id="changeSongLocState${song.id}">Change Value</button></td>
		            	</tr>
				
        			</table>
        			<input type="submit" value="Submit Changes">
            	</form>
            	<button type="button" onclick="off('${song.id}mod-overlay')">Go Back</button>
            	<button type="button" onclick="location.reload()">Escape Modify Mode</button>
            </div>
        </div>
        </#list>
        
        <div class="form-popup" id="myForm">
              <form action="SongServlet" method="POST" class="form-container">
              	<input type=hidden name="option" value="insert">
                <h1>Add Song</h1>
                
                <label for="SongTitle"><b>Song Title:</b></label>
                <input type="text" placeholder="Song Title" name="songTitle" required>
                
                <label for="ArtistName"><b>Artist Name:</b></label>
                <div>
	                <input type="text" list="artistName" name="artistName"  placeholder="Artist Name"  required/>
	                <datalist id="artistName">
	                <#list artists as artist>
	                	<option value="${artist.id}">${artist.name}</option>
	                </#list>
	                </datalist>
	                <input type=hidden name="artist" value="">
                </div>
                
                <label for="URL"><b>Song URL:</b></label>
                <input type="text" placeholder="URL" name="songPath" required>
                
                <label for="GenreName"><b>Genre:</b></label>
                <div>
	                <input type="text" list="genreName" name="genreName"  placeholder="Genre"  required/>
	                <datalist id="genreName">
	                <#list genres as genre>
	                	<option value="${genre.id}">${genre.name}</option>
	                </#list>
	                </datalist>
	                <input type=hidden name="genre" value="">
                </div>
                
                <lable for="local"><b>Local file?:</b></label>
				<select name="local">
					<option value="false">0</option>
					<option value="true">1</option>
				</select>

                <button type="submit" class="btn">Add Song</button>
                <button type="submit" class="btn cancel" onclick="closeForm()">Close</button>
              </form>
        </div>
        
        
    </body>
</HTML>