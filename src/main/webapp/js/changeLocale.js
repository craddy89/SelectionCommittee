function changeLocale(locale) {
    var ref = window.location.href;
    var pos = ref.search('language=');
    if (pos !== -1) {
        window.location.replace(ref.substring(0, pos).concat('language=').concat(locale));
    } else {
        var posCommittee=ref.search('/Committee');
        if(posCommittee === -1){
            window.location.replace(ref.concat('Committee?language=').concat(locale));
        }else {
            window.location.replace(ref.concat('&language=').concat(locale));
        }
    }
}

document.getElementById("rus").addEventListener('click', function () {
    changeLocale('ru');
});
document.getElementById("eng").addEventListener("click", function () {
    changeLocale('en');
});