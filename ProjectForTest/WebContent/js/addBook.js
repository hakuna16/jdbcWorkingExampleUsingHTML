function validation(){
	var bookName = document.getElementById("formGroupExampleInput").value;
	var authourName = document.getElementById("formGroupExampleInput2").value;
	var type = document.getElementById("formGroupExampleInput3").value;
	var stock = document.getElementById("formGroupExampleInput4").value;
	
	if(bookName === ""){
		alert("please enter Book Name");
		return false;
		
	}else if(authourName === ""){
		alert("please enter Authour Name");
		return false;
	}else if(type === ""){
		alert("please enter Type Of Book");
		return false;
	}else if(stock === ""){
		alert("please enter At leaset one book ");
		return false;
	}else if(bookName != "" && authourName != "" && type != "" && stock != ""){
		document.getElementById("addBooks").submit();
	}
	
}