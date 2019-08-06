function insertParam(key, value)
{
    key = encodeURI(key);
    value = encodeURI(value);

    var kvp = document.location.search.substr(1).split('&');

    var i = kvp.length;
    var x;
    while (i--)
    {
        x = kvp[i].split('=');

        if (x[0] == key)
        {
            x[1] = value;
            kvp[i] = x.join('=');
            break;
        }
    }

    if (i < 0) {
        kvp[kvp.length] = [key, value].join('=');
    }

    //this will reload the page, it's likely better to store this until finished
    document.location.search = kvp.join('&');
}
function obtain(content) {
//    var split = content.indexOf(':');
//    var title = content.substring(0, split);
//    var riddle = content.substring(split + 1, content.length);
    $("#qrInTake").val(content);
    $('#submit').trigger('click');
//    insertParam("advance",content);
//    
//    document.getElementById("title").innerHTML = title;
//    document.getElementById("riddle").innerHTML = riddle;
//    

}
function skip() {
    if (confirm("Are you sure you wish to skip? You won't have a second chance to get points for this riddle if you do.") == true) {
        $("#action").val("skip"); 
        $('#actionSubmit').trigger('click');
     }      
}
function redo() {
    $("#action").val("redo"); 
        $('#actionSubmit').trigger('click');
}
if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}


