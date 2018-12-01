<!---------------------------------------------------------------
Project Name: Personal Music Library
Group #: 1
Contributor(s): Najee Searcy, Noah Hickey, Shawein Smith
Document Description:
    This page displays album data. It retrieves the data from
the AlbumsServlet.

Web Technologies: HTML, CSS, Javascript, JQuery
----------------------------------------------------------------->
<HTML lang="en">
    <head>
        <title>Albums</title>
        <link href="http://localhost:8080/MusicLibrary/resources/css/style.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/navbar.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/insertForm.css" rel="stylesheet">
        <link href="http://localhost:8080/MusicLibrary/resources/css/userBar.css" rel="stylesheet">
        <script src="http://localhost:8080/MusicLibrary/resources/scripts/script.js"></script>
        <script type="text/javascript" src="http://localhost:8080/MusicLibrary/resources/scripts/jquery224.min.js"></script>
        <script type="text/javascript" language="javascript">
        	var nowPlaying = -1;
        	
        	function setNowPlaying(imageID) {
        		nowPlaying = imageID;
        	}

        	
        	function setClassIfPlaying(imageID) {
        		if (nowPlaying == imageID) {
        			document.getElementById("img" + imageID).setAttribute("class", "activePlayerImg");
        		} else {
        			return false;
        		}
        	}
        	
        	function showNowPlayingGrid() {
        		<#list albums as album>
        			setClassIfPlaying(${album.id});
        		</#list>
        	}
        	
        	function getGridView(viewCode, sortCode) {
        		var defaultGV = `
					        <div class="row">
					        	<#list albums as album>
					            <div class="column">
					                <div data-tilt> <img src="${album.coverPath}" id="img${album.id}" class="image" style ="width:100%" onclick="onT('${album.name}-overlay', 'YTContainer${album.id}')"> </div>
					                <div class="middle"><div class="text">${album.artist} - ${album.name}</div></div>
					            </div>
					            </#list>
					        </div>
					        <script src ='http://localhost:8080/MusicLibrary/resources/scripts/vanilla-tilt.js'/>
            		`;
            		
        		var defaultGVsortedAscArtist = `
					        <div class="row">
					        	<#list albumsByArtistAZ as album>
					            <div class="column">
					                <div data-tilt> <img src="${album.coverPath}" id="img${album.id}" class="image" style ="width:100%" onclick="onT('${album.name}-overlay', 'YTContainer${album.id}')"> </div>
					                <div class="middle"><div class="text">${album.artist} - ${album.name}</div></div>
					            </div>
					            </#list>
					        </div>
					        <script src ='http://localhost:8080/MusicLibrary/resources/scripts/vanilla-tilt.js'/>
            		`;
            		
        		var defaultGVsortedAscTitle = `
					        <div class="row">
					        	<#list albumsByTitleAZ as album>
					            <div class="column">
					                <div data-tilt> <img src="${album.coverPath}" id="img${album.id}" class="image" style ="width:100%" onclick="onT('${album.name}-overlay', 'YTContainer${album.id}')"> </div>
					                <div class="middle"><div class="text">${album.artist} - ${album.name}</div></div>
					            </div>
					            </#list>
					        </div>
					        <script src ='http://localhost:8080/MusicLibrary/resources/scripts/vanilla-tilt.js'/>
            		`;
        		var deleteGV = `
    				        <div class="row">
					        	<#list albums as album>
					        	<form id="${album.id}" action="AlbumServlet" method=POST>
						            <div class="column">
						            	<input type=hidden name="albumID" value="${album.id}">
						            	<input type=hidden name="option" value="delete">
						            	<div data-tilt> <input type="image" src="${album.coverPath}" id="img${album.id}" class="delImg" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${album.artist} - ${album.name}</div></div>
						            </div>
					            </form>
					            </#list>
	       					 </div>
            		`;
            		
        		var deleteGVsortedAscArtist = `
    				        <div class="row">
					        	<#list albumsByArtistAZ as album>
					        	<form id="${album.id}" action="AlbumServlet" method=POST>
						            <div class="column">
						            	<input type=hidden name="albumID" value="${album.id}">
						            	<input type=hidden name="option" value="delete">
						            	<div data-tilt> <input type="image" src="${album.coverPath}" id="img${album.id}" class="delImg" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${album.artist} - ${album.name}</div></div>
						            </div>
					            </form>
					            </#list>
	       					 </div>
            		`;
            		
        		var deleteGVsortedAscTitle = `
    				        <div class="row">
					        	<#list albumsByTitleAZ as album>
					        	<form id="${album.id}" action="AlbumServlet" method=POST>
						            <div class="column">
						            	<input type=hidden name="albumID" value="${album.id}">
						            	<input type=hidden name="option" value="delete">
						            	<div data-tilt> <input type="image" src="${album.coverPath}" id="img${album.id}" class="delImg" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${album.artist} - ${album.name}</div></div>
						            </div>
					            </form>
					            </#list>
	       					 </div>
            		`;
            		
        		var modifyGV = `
    				        <div class="row">
					        	<#list albums as album>
						            <div class="column">
						            	<div data-tilt> <input type="image" src="${album.coverPath}" id="img${album.id}" class="modImg" onclick="on('${album.name}mod-overlay')" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${album.artist} - ${album.name}</div></div>
						            </div>
					            </#list>
	       					 </div>
            		`;
        		
        		var modifyGVsortedAscArtist = `
    				        <div class="row">
					        	<#list albumsByArtistAZ as album>
						            <div class="column">
						            	<div data-tilt> <input type="image" src="${album.coverPath}" id="img${album.id}" class="modImg" onclick="on('${album.name}mod-overlay')" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${album.artist} - ${album.name}</div></div>
						            </div>
					            </#list>
	       					 </div>
            		`;
            		
        		var modifyGVsortedAscTitle = `
    				        <div class="row">
					        	<#list albumsByTitleAZ as album>
						            <div class="column">
						            	<div data-tilt> <input type="image" src="${album.coverPath}" id="img${album.id}" class="modImg" onclick="on('${album.name}mod-overlay')" style ="width:100%"> </div>
						                <div class="middle"><div class="text">${album.artist} - ${album.name}</div></div>
						            </div>
					            </#list>
	       					 </div>
            		`;
        		
        		if(viewCode == 0){
        			if (sortCode == 0) {
        				return defaultGV;
        			} else if (sortCode == 1) {
        				return defaultGVsortedAscArtist;
        			} else if (sortCode == 2) {
        				return defaultGVsortedAscTitle;
        			}
        		} else if(viewCode == 1) {
        			if (sortCode == 0) {
        				return deleteGV;
        			} else if (sortCode == 1) {
        				return deleteGVsortedAscArtist;
        			} else if (sortCode == 2) {
        				return deleteGVsortedAscTitle;
        			}
        		} else if(viewCode == 2) {
        			if (sortCode == 0) {
        				return modifyGV;
        			} else if (sortCode == 1) {
        				return modifyGVsortedAscArtist;
        			} else if (sortCode == 2) {
        				return modifyGVsortedAscTitle;
        			}
        		}
        		
       			
        		
        	}
        	
        	function getTableView(viewCode, sortCode) {
        		var defaultTV = `
            			 <div>
				            <table id="t2" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Album Title</th>
				                </tr>
				                <#list albums as album>
				                <tr onclick="onT('${album.name}-overlay', 'YTContainer${album.id}')">
				                    <td>${album.artist}</td>
				                    <td>${album.name}</td>
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
				                    <th>Album Title</th>
				                </tr>
				                <#list albumsByArtistAZ as album>
				                <tr onclick="onT('${album.name}-overlay', 'YTContainer${album.id}')">
				                    <td>${album.artist}</td>
				                    <td>${album.name}</td>
				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
            		
            	var defaultTVsortedAscTitle = `
            			 <div>
				            <table id="t2" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Album Title</th>
				                </tr>
				                <#list albumsByTitleAZ as album>
				                <tr onclick="onT('${album.name}-overlay', 'YTContainer${album.id}')">
				                    <td>${album.artist}</td>
				                    <td>${album.name}</td>
				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
            		
        		var deleteTV = `
            			 <div>
				            <table id="tblDEL" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Album Title</th>
				                </tr>
				                <#list albums as album>
				                <tr>
				                    <td>${album.artist}					                   
					            	</td>
				                    <td>${album.name}"</td>
				                    <td>
				                    <form id="${album.id}" action=AlbumServlet method=POST>
						            	<input type=hidden name="albumID" value="${album.id}">
						            	<input type=hidden name="option" value="delete">
						            	<input type=submit value="delete">
						            </form>
				                    </td>

				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
            		
        		var deleteTVsortedAscArtist = `
            			 <div>
				            <table id="tblDEL" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Album Title</th>
				                </tr>
				                <#list albumsByArtistAZ as album>
				                <tr>
				                    <td>${album.artist}					                   
					            	</td>
				                    <td>${album.name}"</td>
				                    <td>
				                    <form id="${album.id}" action=AlbumServlet method=POST>
						            	<input type=hidden name="albumID" value="${album.id}">
						            	<input type=hidden name="option" value="delete">
						            	<input type=submit value="delete">
						            </form>
				                    </td>

				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
            		
	        		var deleteTVsortedAscTitle = `
            			 <div>
				            <table id="tblDEL" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Album Title</th>
				                </tr>
				                <#list albumsByTitleAZ as album>
				                <tr>
				                    <td>${album.artist}					                   
					            	</td>
				                    <td>${album.name}"</td>
				                    <td>
				                    <form id="${album.id}" action=AlbumServlet method=POST>
						            	<input type=hidden name="albumID" value="${album.id}">
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
				                    <th>Album Title</th>
				                </tr>
				                <#list albums as album>
				                <tr onclick="on('${album.name}mod-overlay')">
				                    <td>${album.artist}					                   
					            	</td>
				                    <td>${album.name}"</td>
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
				                    <th>Album Title</th>
				                </tr>
				                <#list albumsByArtistAZ as album>
				                <tr onclick="on('${album.name}mod-overlay')">
				                    <td>${album.artist}					                   
					            	</td>
				                    <td>${album.name}"</td>
				                </tr>
				                </#list>
				            </table>
				        </div>
            		`;
            		
        		var modifyTVsortedAscTitle = `
            			 <div>
				            <table id="tblMOD" style="width:100%">
				                <tr>
				                    <th>Artist</th>
				                    <th>Album Title</th>
				                </tr>
				                <#list albumsByTitleAZ as album>
				                <tr onclick="on('${album.name}mod-overlay')">
				                    <td>${album.artist}					                   
					            	</td>
				                    <td>${album.name}"</td>
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
        			} else if (sortCode == 2) {
        				return defaultTVsortedAscTitle;
        			}
        		} else if(viewCode == 1) {
        			if (sortCode == 0) {
        				return deleteTV;
        			} else if (sortCode == 1) {
        				return deleteTVsortedAscArtist;
        			} else if (sortCode == 2) {
        				return deleteTVsortedAscTitle;
        			}
        		} else if(viewCode == 2) {
        			if (sortCode == 0) {
        				return modifyTV;
        			} else if (sortCode == 1) {
        				return modifyTVsortedAscArtist;
        			} else if (sortCode == 2) {
        				return modifyTVsortedAscTitle;
        			}
        		}
        	}
        	
        	function pickOrder() {	
            		var gridView = getGridView(0,0);
            		var gridViewSortedByArtistAZ = getGridView(0,1);
            		var gridViewSortedByTitleAZ = getGridView(0,2);
            		
            		var tableView = getTableView(0,0);
            		var tableViewSortedByArtistAZ =  getTableView(0,1);
            		var tableViewSortedByTitleAZ = getTableView(0,2);
            		
            		var contentDiv = document.getElementById("content");
            		var option = document.getElementById("selectSort").value;
            		
            		
            		if (contentDiv.firstElementChild.className == "row") {
            			
	                    if (option == "default") {
                        $("#content").html(gridView);
                    	} else if (option == "AZArtist") {
                        $("#content").html(gridViewSortedByArtistAZ);
                        } else if (option == "AZTitle") {
                        $("#content").html(gridViewSortedByTitleAZ);
                    	}
                    	showNowPlayingGrid();
            		} else {
	                    if (option == "default") {
                        	$("#content").html(tableView);
                    	} else if (option == "AZArtist") {
                        	$("#content").html(tableViewSortedByArtistAZ);
            			} else if (option == "AZTitle") {
                        $("#content").html(tableViewSortedByTitleAZ);
                        }
        			}
        	}
            $(document).ready(function() {
            	$("#PlayInBG").click(function() {
            		alert("do something");
            	});
            	
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
            		var createFormGridArtistAZ = getGridView(1,1);
            		var createFormGridTitleAZ = getGridView(1,2);
            		
            		var tableFormView = getTableView(1,0);
            		var tableFormViewArtistAZ = getTableView(1,1);
            		var tableFormViewTitleAZ = getTableView(1,2)
            		
            		var contentDiv = document.getElementById("content");
            		var option = document.getElementById("selectSort").value;
            		
            		if (contentDiv.firstElementChild.className == "row") {
	                    if (option == "default") {
	                        $("#content").html(createFormGrid);
                    	} else if (option == "AZArtist") {
	                        $("#content").html(createFormGridArtistAZ);
                        } else if (option == "AZTitle") {
	                        $("#content").html(createFormGridTitleAZ);
                 		}
                 		
                 		showNowPlayingGrid();
            		} else {
	                    if (option == "default") {
	                    	$("#content").html(tableFormView);
	                	} else if (option == "AZArtist") {
	                    	$("#content").html(tableFormViewArtistAZ);
	        			} else if (option == "AZTitle") {
	                    $("#content").html(tableFormViewTitleAZ);
	                    }
            		}
            		
            	});
            	
            	$("#modButton").click(function() {
             		var createFormGrid = getGridView(2,0);
            		var createFormGridArtistAZ = getGridView(2,1);
            		var createFormGridTitleAZ = getGridView(2,2);
            		
            		var tableFormView = getTableView(2,0);
            		var tableFormViewArtistAZ = getTableView(2,1);
            		var tableFormViewTitleAZ = getTableView(2,2);
            		
            		var contentDiv = document.getElementById("content");
            		var option = document.getElementById("selectSort").value;
            		
            		if (contentDiv.firstElementChild.className == "row") {
	                    if (option == "default") {
                        $("#content").html(createFormGrid);
                    	} else if (option == "AZArtist") {
                        $("#content").html(createFormGridArtistAZ);
                        } else if (option == "AZTitle") {
                        $("#content").html(createFormGridTitleAZ);
                        }
                        showNowPlayingGrid();
            		} else {
	                   if (option == "default") {
                        	$("#content").html(tableFormView);
                    	} else if (option == "AZArtist") {
                        	$("#content").html(tableFormViewArtistAZ);
            			} else if (option == "AZTitle") {
                        $("#content").html(tableFormViewTitleAZ);
                        }
            		}
            		
            	});
            	
            	<#list albums as album>
            	$("#changeAlbumName${album.id}").click(function() {
            		var miniForm = "<td><input type='text' name='albumName'></td>";
        			$("#nameVal${album.id}").html(miniForm);
            	});
            	
            	$("#changeArtist${album.id}").click(function() {
            		var miniForm = "<td><input type='text' name='artistName'></td>";
        			$("#artistVal${album.id}").html(miniForm);
            	});
            	
            	$("#changeImagePath${album.id}").click(function() {
            		var miniForm = "<td><input type='text' name='imagePath'></td>";
        			$("#imagePathVal${album.id}").html(miniForm);
            	});
            	
            	$("#changeAlbumPlaylist${album.id}").click(function() {
            		var miniForm = "<td><input type='text' name='playlist'></td>";
        			$("#playlistVal${album.id}").html(miniForm);
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
               	<button class="dropbtn">Albums</button>
                <div class="dropdown-content">
                    <a onclick="openForm()">Add</a>
                    <a id="delButton">Delete</a>
                    <a id="modButton">Modify</a>
                </div>
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
			<option id="AZTitle" value="AZTitle">Album Title (ascending)</option>
			<option id="AZArtist" value="AZArtist">Artist Name (ascending)</option>
			</select>
       </div>
        
        <div id="content">
        <!--This section holds album images in a grid-->
        </div>
        
        <!--This section holds the overlay of album details-->
        <#list albums as album>
        <div class="overlay" id="${album.name}-overlay" >
            <div id="otext" style="height: 600px; overflow: auto;">  
                <h1>${album.name}</h1>
                <p>Artist: ${album.artist}</p>
                <h2>Embedded Playlist:</h2>
                <div firstClick="false" id="YTContainer${album.id}" class="YT">${album.playlistPath}</div>
                <br><button type="button" onclick="offT('${album.name}-overlay',  'YTContainer${album.id}', '${album.playlistPath}'); resetImageClass('img${album.id}'); setNowPlaying('-1')">Exit Album Player</button>
                <br><button type="button" id="playInBG" onclick="playInBG('${album.name}-overlay', 'img${album.id}'); setNowPlaying('${album.id}')">Play In Background</button>
                <div id="comments">
	                	<table id="" style="width:100%; bgcolor:white; color:white; height: 200px;">
						  <tr>
						    <th>User</th>
						    <th>Comment</th> 
						  </tr>
						  <#list comments as comment>
						  <#if comment.albumID == (album.id?string)>
						  <tr>
						    <td>${comment.userName}</td>
						    <td>${comment.content}</td>
						    <#if (activeUser.userName == comment.userName) || (activeUser.userName?matches("admin") == true)>
						    <td><form action=AlbumServlet method=POST>
						    	<input type=hidden name="commentID" value="${comment.id}">
						    	<input type=hidden name="option" value="deleteComment">
						    	<input type=submit value="Delete">
						    </form></td>
						    </#if>
						  </tr>
						  </#if>
						  </#list>
						  <tr>
						  	<form action=AlbumServlet method=POST>
							<input type=hidden name="userID" value="${activeUser.userID}">
							<input type=hidden name="albumID" value="${album.id}">
						  	<input type=hidden name="option" value="addComment">
						  	<td style="font-style: italic;">${activeUser.userName}</td>
							<td><input type=text name="content"></td>						  	
						  	<td><input type=submit value="Add"></td>
						  	</form>
						  </tr>
						  
						</table>
                </div>
            </div>
        </div>
        </#list>
        
        <!-- This section hold the modification forms -->
        <#list albums as album>
        <div class="overlay" id="${album.name}mod-overlay" >
            <div id="otext">
            	<form action="AlbumServlet" method="POST">
		            <table bgcolor="white" id="t2" style="width:100%">
		            <input type="hidden" name="option" value="modify">
		            <input type="hidden" name="albumID" value="${album.id}">
		            	<tr>
		            		<th>Field</th>
			            	<th>Current Value</th>
			            	<th>New Value</th>
		            	</tr>
		            	<tr>
		            		<td>Album Name</td>
		            		<td>${album.name}</td>
		            		<td id="nameVal${album.id}"><button type="button" id="changeAlbumName${album.id}">Change Value</button></td>
		            	</tr>
		            	<tr>
		                    <td>Album Artist</td>
		                    <td>${album.artist}</td>
		                    <td id="artistVal${album.id}"><button type="button" id="changeArtist${album.id}">Change Value</button></td>
		            	</tr>
		            	
		            	<tr>
		            		<td>Cover Path</td>
		            		<td>${album.coverPath}</td>
		            		<td id="imagePathVal${album.id}"><button type="button" id="changeImagePath${album.id}">Change Value</button></td>
		            	</tr>
		           
		                <tr>
							<td>Playlist Path</td>
							<td>${album.playlistPath}</td>
							<td id="playlistVal${album.id}"><button type="button" id="changeAlbumPlaylist${album.id}">Change Value</button></td>
		                </tr>
		                
				
        			</table>
        			<input type="submit" value="Submit Changes">
            	</form>
            	<button type="button" onclick="off('${album.name}mod-overlay')">Go Back</button>
            	<button type="button" onclick="pickOrder(); off('${album.name}mod-overlay')">Escape Modify Mode</button>
            </div>
        </div>
        </#list>
		
        <div class="form-popup" id="myForm">
              <form action="AlbumServlet" method="POST" class="form-container">
              	<input type=hidden name="option" value="insert">
                <h1>Add Album</h1>

                <label for="AlbumName"><b>Album Name:</b></label>
                <input type="text" placeholder="Album Title" name="albumName" required>
				
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
                
                <label for="ImagePath"><b>Image Path:</b></label>
                <input type="text" placeholder="(...).jpg" name="imagePath" required>

                <label for="URL"><b>YouTube URL:</b></label>
                <input type="text" placeholder="URL" name="playlist" required>
                
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


                <button type="submit" class="btn">Add Album</button>
                <button type="submit" class="btn cancel" onclick="closeForm()">Close</button>
              </form>
        </div>
    </body>
</HTML>