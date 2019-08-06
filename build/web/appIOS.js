
    console.log("test1");
		var qr = new Instascan.Scanner({
			video: document.getElementById("scanner")
		});
		console.log("test2");
		qr.addListener('scan', function(data) {
			console.log(data);
			obtain(data);
			alert("You found me! Press OK to see your next riddle");
		});
		console.log("test3");
		Instascan.Camera.getCameras().then(function(cams) {
			qr.start(cams[0]);
		}).catch(function(err) {
			console.log(err);
		});
        console.log("test4");



