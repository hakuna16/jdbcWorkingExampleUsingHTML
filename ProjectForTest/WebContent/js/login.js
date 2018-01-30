function validation(){
	var email = document.getElementById("exampleInputEmail1").value;
	var password = document.getElementById("exampleInputPassword1").value;
	
	if(email === ""){
		alert("please enter Email")
		return false;
		
	}else if(password === ""){
		alert("please enter password")
		return false;
	}else if(email != "" && password != ""){
		document.getElementById("loginForm").submit();
	}
	
}