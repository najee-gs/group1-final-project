/*****************************************************
Project Name: Personal Music Library
Group #: 1
Contributor(s): Joey Brett
Document Description: This document contains JavaScript definitions for the login page.
******************************************************/

var changeBackGround = function(){
  var mainBody = document.getElementById("mainBody");
  if(document.getElementById("selectBackGround").value === "Original"){
	  mainBody.style.backgroundImage = "url('resources/imgs/background2.jpg')";
  }else if(document.getElementById("selectBackGround").value === "GreyScale"){
	  mainBody.style.backgroundImage = "url('resources/imgs/greyscale.jpg')";
  }else{
	  mainBody.style.backgroundImage = "url('resources/imgs/colored.jpg')";
  }
  mainBody.style.backgroundImage = document.getElementById("selectBackGround").value;
};