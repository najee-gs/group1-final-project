<!---------------------------------------------------------------
Project Name: Personal Music Library
Group #: 1
Contributor(s): Najee Searcy, Shawein Smith, Noah Hickey
Document Description:
    This page displays artist data. It retrieves the data from
the ArtistServlet.

Web Technologies: HTML, CSS, Javascript, JQuery
----------------------------------------------------------------->
<HTML lang="en">
    <head>
        <title>Artists</title>
        <link href="http://localhost:8080/MusicLibrary/resources/css/style.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/navbar.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/insertForm.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/userBar.css" rel="stylesheet">
        <script src="http://localhost:8080/MusicLibrary/resources/scripts/script.js"></script>
        <script type="text/javascript" src="http://localhost:8080/MusicLibrary/resources/scripts/jquery224.min.js"></script>
        <script type="text/javascript" language="javascript">
        	function getGridView(viewCode, sortCode) {
        		var defaultGV = `
					        <div class="row">
					        	<#list artists as artist>
						            <div class="column">
					                <div data-tilt> <img src="${artist.imagePath}" class="image" style ="width:100%" onclick="on('${artist.name}-overlay')"> </div>
					                <div class="middle"><div class="text">${artist.name}</div></div>
					            	</div>
					        	</#list>
					        </div>
					        <script src ='http://localhost:8080/MusicLibrary/resources/scripts/vanilla-tilt.js'/>
            		`;
            		
        		var defaultGVsortedAscArtist = `
					        <div class="row">
					        	<#list artistsAZ as artist>
						            <div class="column">
					                <div data-tilt> <img src="${artist.imagePath}" class="image" style ="width:100%" onclick="on('${artist.name}-overlay')"> </div>
					                <div class="middle"><div class="text">${artist.name}</div></div>
					            	</div>
					        	</#list>
					        </div>
					        <script src ='http://localhost:8080/MusicLibrary/resources/scripts/vanilla-tilt.js'/>
            		`;
        		
        		var deleteGV = `
				 	        <div class="row">
					        	<#list artists as artist>
					        	<form id="${artist.id}" action="ArtistServlet" method=POST>
						            <div class="column">
						            	<input type=hidden name="artistID" value="${artist.id}">
						            	<input type=hidden name="option" value="delete">
						                <div data-tilt> <input type="image" src="${artist.imagePath}" class="delImg" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${artist.name}</div></div>
					            	</div>
					            </form>
					        	</#list>
					        </div>
            		`;
            		
        		var deleteGVsortedAscArtist = `
				 	        <div class="row">
					        	<#list artistsAZ as artist>
					        	<form id="${artist.id}" action="ArtistServlet" method=POST>
						            <div class="column">
						            	<input type=hidden name="artistID" value="${artist.id}">
						            	<input type=hidden name="option" value="delete">
						                <div data-tilt> <input type="image" src="${artist.imagePath}" class="delImg" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${artist.name}</div></div>
					            	</div>
					            </form>
					        	</#list>
					        </div>
            		`;
            		
        		var modifyGV = `
				 	        <div class="row">
					        	<#list artists as artist>
						            <div class="column">
						                <div data-tilt> <input type="image" src="${artist.imagePath}" class="modImg" onclick="on('${artist.name}mod-overlay')" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${artist.name}</div></div>
					            	</div>
					        	</#list>
					        </div>
            		`;
        		
        		var modifyGVsortedAscArtist = `
				 	        <div class="row">
					        	<#list artistsAZ as artist>
						            <div class="column">
						                <div data-tilt> <input type="image" src="${artist.imagePath}" class="modImg" onclick="on('${artist.name}mod-overlay')" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${artist.name}</div></div>
					            	</div>
					        	</#list>
					        </div>
            		`;
        		
        		if(viewCode == 0){
        			if (sortCode == 0) {
        				return defaultGV;
        			} else if (sortCode == 1) {
        				return defaultGVsortedAscArtist;
        			}
        		} else if(viewCode == 1) {
        			if (sortCode == 0) {
        				return deleteGV;
        			} else if (sortCode == 1) {
        				return deleteGVsortedAscArtist;
        			}
        		} else if(viewCode == 2) {
        			if (sortCode == 0) {
        				return modifyGV;
        			} else if (sortCode == 1) {
        				return modifyGVsortedAscArtist;
        			}
        		}
        		
       			
        		
        	}
        	
        	function getTableView(viewCode, sortCode) {
        		var defaultTV = `
            			 <div>
				            <table id="t2" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Debut</th>
				                </tr>
				                <#list artists as artist>
				                <tr onclick="on('${artist.name}-overlay')">
				                    <td>${artist.name}</td>
				                    <td>${artist.debut}</td>
				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
        		var defaultTVsortedAscArtist = `
            			 <div>
				            <table id="t2" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Debut</th>
				                </tr>
				                <#list artistsAZ as artist>
				                <tr onclick="on('${artist.name}-overlay')">
				                    <td>${artist.name}</td>
				                    <td>${artist.debut}</td>
				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
        		var deleteTV  = `
              			 <div>
				            <table id="tblDEL" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Debut</th>
				                </tr>
				                <#list artists as artist>
				                <tr>
				                    <td>${artist.name}</td>
				                    <td>${artist.debut}</td>
				                    <td>
				                    <form id="${artist.id}" action=ArtistServlet method=POST>
						            	<input type=hidden name="artistID" value="${artist.id}">
						            	<input type=hidden name="option" value="delete">
						            	<input type=submit value="delete">
						            </form>
						            </td>
				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
            		
        		var deleteTVsortedAscArtist  = `
              			 <div>
				            <table id="tblDEL" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Debut</th>
				                </tr>
				                <#list artists as artist>
				                <tr>
				                    <td>${artist.name}</td>
				                    <td>${artist.debut}</td>
				                    <td>
				                    <form id="${artist.id}" action=ArtistServlet method=POST>
						            	<input type=hidden name="artistID" value="${artist.id}">
						            	<input type=hidden name="option" value="delete">
						            	<input type=submit value="delete">
						            </form>
						            </td>
				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
        		var modifyTV = `
              			 <div>
				            <table id="tblMOD" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Debut</th>
				                </tr>
				                <#list artists as artist>
					                <tr onclick="on('${artist.name}mod-overlay')">
					                    <td>${artist.name}</td>
					                    <td>${artist.debut}</td>
					                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
            		
        		var modifyTVsortedAscArtist = `
              			 <div>
				            <table id="tblMOD" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Debut</th>
				                </tr>
				                <#list artistsAZ as artist>
					                <tr onclick="on('${artist.name}mod-overlay')">
					                    <td>${artist.name}</td>
					                    <td>${artist.debut}</td>
					                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
        		
        		if(viewCode == 0){
        			if (sortCode == 0) {
        				return defaultTV;
        			} else if (sortCode == 1) {
        				return defaultTVsortedAscArtist;
        			}
        		} else if(viewCode == 1) {
        			if (sortCode == 0) {
        				return deleteTV;
        			} else if (sortCode == 1) {
        				return deleteTVsortedAscArtist;
        			}
        		} else if(viewCode == 2) {
        			if (sortCode == 0) {
        				return modifyTV;
        			} else if (sortCode == 1) {
        				return modifyTVsortedAscArtist;
        			}
        		}
        	}
        	
        	function pickOrder() {	
            		var gridView = getGridView(0,0);
            		var gridViewSortedAZ = getGridView(0,1);
            		
            		var tableView = getTableView(0,0);
            		var tableViewSortedAZ =  getTableView(0,1);
            		
            		var contentDiv = document.getElementById("content");
            		var option = document.getElementById("selectSort").value;
            		
            		if (contentDiv.firstElementChild.className == "row") {
            			
	                    if (option == "default") {
                        $("#content").html(gridView);
                    	} else if (option == "AZArtist") {
                        $("#content").html(gridViewSortedAZ);
                    }
            		} else {
	                    if (option == "default") {
                        	$("#content").html(tableView);
                    	} else if (option == "AZArtist") {
                        	$("#content").html(tableViewSortedAZ);
            			}
        			}
        	}

            $(document).ready(function() {
            	
            	$("#content").html(getGridView(0,0));
            	
            	$("#slct").click(function() {
            		var tableView = getTableView(0,0);
					var gridView = getGridView(0,0);
            		
                    var option = document.getElementById("slct").value;
                    if (option == "t") {
                        $("#content").html(tableView);
                        pickOrder();
                    } else if (option == "g") {
                        $("#content").html(gridView);
                        pickOrder();
                    }
            		
            	});
            	
            	$("#selectSort").click(function() {
            		pickOrder();
            	});
            	
            	$("#delButton").click(function() {
            		var createFormGrid = getGridView(1,0);
            		var createFormGridAZ = getGridView(1,1);
            		
            		var tableFormView = getTableView(1,0);
            		var tableFormViewAZ = getTableView(1,1);
            		
            		var contentDiv = document.getElementById("content");
            		var option = document.getElementById("selectSort").value;
            		
            		if (contentDiv.firstElementChild.className == "row") {
	                    if (option == "default") {
                        $("#content").html(createFormGrid);
                    	} else if (option == "AZArtist") {
                        $("#content").html(createFormGridAZ);
                        }
            		} else {
	                   if (option == "default") {
                        	$("#content").html(tableFormView);
                    	} else if (option == "AZArtist") {
                        	$("#content").html(tableFormViewAZ);
            			}
            		}
            		
            	});
            	
            	$("#modButton").click(function() {
             		var createFormGrid = getGridView(2,0);
            		var createFormGridAZ = getGridView(2,1);
            		
            		var tableFormView = getTableView(2,0);
            		var tableFormViewAZ = getTableView(2,1);
            		
            		var contentDiv = document.getElementById("content");
            		var option = document.getElementById("selectSort").value;
            		
            		if (contentDiv.firstElementChild.className == "row") {
	                    if (option == "default") {
                        $("#content").html(createFormGrid);
                    	} else if (option == "AZArtist") {
                        $("#content").html(createFormGridAZ);
                        }
            		} else {
	                   if (option == "default") {
                        	$("#content").html(tableFormView);
                    	} else if (option == "AZArtist") {
                        	$("#content").html(tableFormViewAZ);
            			}
            		}
            		
            	});
            	
            	<#list artists as artist>
            	$("#changeArtistName${artist.id}").click(function() {
            		var miniForm = "<td><input type='text' name='artistName'></td>";
        			$("#nameVal${artist.id}").html(miniForm);
            	});
            	
            	$("#changeArtistDebut${artist.id}").click(function() {
            		var miniForm = "<td><input type='text' name='debut'></td>";
        			$("#debutVal${artist.id}").html(miniForm);
            	});
            	
            	$("#changeArtistImagePath${artist.id}").click(function() {
            		var miniForm = "<td><input type='text' name='imagePath'></td>";
        			$("#imagePathVal${artist.id}").html(miniForm);
            	});
            	
            	$("#changeArtistInstagram${artist.id}").click(function() {
            		var miniForm = "<td><input type='text' name='instagram'></td>";
        			$("#instaVal${artist.id}").html(miniForm);
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
                <button href="#" class="dropbtn focus">Artists</button>
                <div class="dropdown-content">
                    <a onclick="openForm()">Add</a>
                    <a id="delButton">Delete</a>
                    <a id="modButton">Modify</a>
                </div>
            </div>
            <div class="dropdown">
                <a class="dropbtn" href="http://localhost:8080/MusicLibrary/AlbumServlet">Albums</a>
            </div>
            <div class="dropdown">
                <a class="dropbtn" href="http://localhost:8080/MusicLibrary/SongServlet">Songs</a>
            </div> 
        </div>
        
        <div class="displayOptions">
        	<div>
			Active User(s):
			<#list activeUsers as user>
				${user.userName}
			</#list>
			<form action="HomeServlet" method=POST class="form">
				<input type=hidden name="option" value="logout">
				<input type=hidden name="userName" value="${activeUser.userName}">
				<input type="submit" value="logout?">
			</form>
			</div>
			Layout:
			<select id="slct">
			<option id="grd" value="g">Grid Layout</option>
			<option id="tbl" value="t">Table Layout</option>
			</select>
			Sort By:
			<select id="selectSort">
			<option id="default" value="default">Order Added</option>
			<option id="AZArtist" value="AZArtist">Artist Name (ascending)</option>
			</select>
       </div>
      
       
       
       <div id="content">
	        <!--This section holds artist images in a grid-->
	   </div>
	   
        <!--This section holds the overlay biography pages-->
        <#list artists as artist>
	        <div class="overlay" id="${artist.name}-overlay" >
	            <div id="otext" style="height: 600px; overflow: auto;">            
	                <h1>${artist.name}</h1>
	                <a href="${artist.insta}"><img src="http://localhost:8080/MusicLibrary/resources/icon-imgs/insta.png"></a>
	                <p>Rest of bio goes here etc.</p>
	                <div id="comments">
	                	<table id="" style="width:100%; bgcolor:white; color:white;">
						  <tr>
						    <th>User</th>
						    <th>Comment</th> 
						  </tr>
						  <#list comments as comment>
						  <#if comment.artistID == (artist.id?string)>
						  <tr>
						    <td>${comment.userName}</td>
						    <td>${comment.content}</td>
						    <#if (activeUser.userName == comment.userName) || (activeUser.userName?matches("admin") == true)>
						    <td><form action=ArtistServlet method=POST>
						    	<input type=hidden name="commentID" value="${comment.id}">
						    	<input type=hidden name="option" value="deleteComment">
						    	<input type=submit value="Delete">
						    </form></td>
						    </#if>
						  </tr>
						  </#if>
						  </#list>
						  <tr>
						  	<form action=ArtistServlet method=POST>
							<input type=hidden name="userID" value="${activeUser.userID}">
							<input type=hidden name="artistID" value="${artist.id}">
						  	<input type=hidden name="option" value="addComment">
						  	<td style="font-style: italic;">${activeUser.userName}</td>
							<td><input type=text name="content"></td>						  	
						  	<td><input type=submit value="Add"></td>
						  	</form>
						  </tr>
						  
						</table>
	                </div>
	                <button type="button" onclick="off('${artist.name}-overlay')">Go Back</button>
	            </div>
	        </div>
        </#list>
        
        <!-- This section hold the modification forms -->
        <#list artists as artist>
        <div class="overlay" id="${artist.name}mod-overlay" >
            <div id="otext">
            	<form action="ArtistServlet" method="POST">
		            <table bgcolor="white" id="t2" style="width:100%">
		            <input type="hidden" name="option" value="modify">
		            <input type="hidden" name="artistID" value="${artist.id}">
		            	<tr>
		            		<th>Field</th>
			            	<th>Current Value</th>
			            	<th>New Value</th>
		            	</tr>
		            	<tr>
		            		<td>Name</td>
		            		<td>${artist.name}</td>
		            		<td id="nameVal${artist.id}"><button type="button" id="changeArtistName${artist.id}">Change Value</button></td>
		            	</tr>
		            	<tr>
		                    <td>Debut</td>
		                    <td>${artist.debut}</td>
		                    <td id="debutVal${artist.id}"><button type="button" id="changeArtistDebut${artist.id}">Change Value</button></td>
		            	</tr>
		            	
		            	<tr>
		            		<td>Image Path</td>
		            		<td>${artist.imagePath}</td>
		            		<td id="imagePathVal${artist.id}"><button type="button" id="changeArtistImagePath${artist.id}">Change Value</button></td>
		            	</tr>
		           
		                <tr>
							<td>Instagram</td>
							<td>${artist.insta}</td>
							<td id="instaVal${artist.id}"><button type="button" id="changeArtistInstagram${artist.id}">Change Value</button></td>
		                </tr>
				
        			</table>
        			<input type="submit" value="Submit Changes">
            	</form>
            	<button type="button" onclick="off('${artist.name}mod-overlay')">Go Back</button>
            	<button type="button" onclick="location.reload()">Escape Modify Mode</button>
            </div>
        </div>
        </#list>
        
        <div class="form-popup" id="myForm">
              <form action="ArtistServlet" method="POST" class="form-container">
              	<input type=hidden name="option" value="insert">
                <h1>Add Artist</h1>

                <label for="ArtistName"><b>Artist Name:</b></label>
                <input type="text" placeholder="Artist Name" name="artistName" required>
                
                <label for="Debut"><b>Debut:</b></label>
                <div>
	                <input type="text" list="artistDebut" name="artistDebut"  placeholder="Debut"  required/>
	                <datalist id="artistDebut">
	                <#list artists as artist>
	                	<option value="${artist.debut}">${artist.debut}</option>
	                </#list>
	                </datalist>
	                <input type=hidden name="debut" value="">
                </div>
                
                <label for="ImagePath"><b>Image Path:</b></label>
                <input type="text" placeholder="image.jpg" name="imagePath" required>

                <label for="URL"><b>Instagram:</b></label>
                <input type="text" placeholder="URL" name="instagram" required>

                <button type="submit" class="btn">Add Artist</button>
                <button type="submit" class="btn cancel" onclick="closeForm()">Close</button>
              </form>
        </div>
        
       <script src ="http://localhost:8080/MusicLibrary/resources/scripts/vanilla-tilt.js"></script>
    </body>
</HTML>
