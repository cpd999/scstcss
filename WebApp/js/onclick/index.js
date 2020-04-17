function link_dataS(data){
	var base = new Base64();
	let md5pp = base.encode(data);
	window.location.href="DataS.html?"+md5pp;
}