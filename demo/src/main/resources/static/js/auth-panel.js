f (loggedIn) {
        document.getElementById("login-btn").style.display = "none";
        document.getElementById("logout-btn").style.display = "inline";
        document.getElementById("user-panel").style.display = "inline";
    } else {
        document.getElementById("login-btn").style.display = "inline";
        document.getElementById("logout-btn").style.display = "none";
        document.getElementById("user-panel").style.display = "none";
    }
});


function showUserProfile() {

    window.location.href = '/panel-uzytkownika';
}