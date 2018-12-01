/***********************************************************
Project Name: Personal Music Library Front End Mockup Version 1
Group #: 1
Contributor(s): Najee Searcy
Document Description:
************************************************************/
function on(x) {
    var n = x;
    document.getElementById(n).style.display = "block";
}

function onT(x, y) {
	if(document.getElementById(y).getAttribute("firstClick") == "false") {
		loadVideo(y);
	}
    document.getElementById(x).style.display = "block";
    document.getElementById(y).style.display = "block";
}

function off(x) {
    var n = x;
    document.getElementById(n).style.display = "none";
}

function offT(x, y, z) {
    var n = x;
    stopVideo(y, z);
    document.getElementById(n).style.display = "none";
}

function playInBG(x, y) {
	var n = x;
    document.getElementById(n).style.display = "none";
    document.getElementById(y).setAttribute("class", "activePlayerImg");
    
}

function resetImageClass(x) {
	var n = x;
    document.getElementById(n).setAttribute("class", "image");
}

function resetAllImageClasses() {
	var n = "modImg"
    var listOfModImgs = document.getElementsByClassName(n);
	var count = 0;
	var total = listOfModImgs.length;
	var element;
	for (count; count < total; count++) {
		element = listOfModImgs[0].getAttribute("id");
		window.alert(element);
	    document.getElementById(element).setAttribute("class", "image");
	}
}

function del() {
    window.alert("Are you sure you want to delete this?");
    location.reload();
}

function delOptions() {
	var imgs = document.getElementsByClassName("image");
    var i = 0;
    var l = imgs.length;

    for (i; i < l; i++) {
        imgs[0].onclick = del;
        imgs[0].className = "delImg";
    }
	
}

function showOptions() {
    var currentState = document.getElementById("op");
    if(currentState.style.display.match("none")) {
        document.getElementById("op").style.display = "block";
    } else {
        document.getElementById("op").style.display = "none";
    }
    
}

function loadVideo(y) {
	var path = document.getElementById(y).innerHTML;
	if(path != null){
		document.getElementById(y).innerHTML = "<iframe width='640' height='360' src='https://www.youtube.com/embed/videoseries?"+ path + "&rel=0 ' frameborder='0' allow='autoplay; encrypted-media' allowfullscreen></iframe>";
	}
	document.getElementById(y).setAttribute("firstClick", "true");
}

function stopVideo(y, z) {
	var path = document.getElementById(y).innerHTML = z;
	document.getElementById(y).setAttribute("firstClick", "false");
	document.getElementById(y).setAttribute("class", "image");
}

function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

function test() {
	var albumName = "${album.name}";
	var albumArtist = "${album.artist}";
	var albumID = "${album.id}";
    var tableView = `
		 <div>
           <table id="t2" style="width:100%">
               <tr>
                   <th>Artist</th>
                   <th>Album Title</th>
               </tr>
               <#list albums as album>
               <tr onclick="onT('`+ albumName +`-overlay', 'YTContainer`+ albumID +`')">
                   <td>`+ albumArtist +`</td>
                   <td>`+ albumName +`</td>
               </tr>
               </#list>
           </table>
       </div>
	`;
    
    
	return tableView;
}
