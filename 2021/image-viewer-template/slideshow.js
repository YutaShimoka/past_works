let i = 0;

slideshowHandler();

function slideshowHandler() {
    if (i == SOURCE_LIST.length) {
        i = 0;
    }
    document.getElementById("myImage").src = pathDir + SOURCE_LIST[i++];
    setTimeout("slideshowHandler()", 1000);
}
