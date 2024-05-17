document.addEventListener('DOMContentLoaded', function() {
    let i = 0;
    let username = document.getElementById("usernameHidden").value.trim();
    let txt = 'Welcome, '+ username;
    let speed = 50;

    function typeWriter() {
        if (i < txt.length) {
            document.getElementById("type-writing").innerHTML += txt.charAt(i);
            i++;
            setTimeout(typeWriter, speed);
        }else{
            const welcomeText = document.querySelector('.welcome-text');
            welcomeText.classList.add('show')
        }
    }
    typeWriter();
});