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
            welcomeText.classList.add('show');
            //show the button and event list after the animation
            const addButton = document.getElementById('add-event-button');
            const eventsContainer = document.querySelector('.events-container');
            const slothyMain = document.getElementById('slothy-main');
            const logoutButton = document.querySelector('.logout-container');

            setTimeout(() => {
                addButton.classList.add('show');
                eventsContainer.classList.add('show');
                logoutButton.classList.add('show');
            }, 1000);
            setTimeout(() => {
                slothyMain.classList.add('show');
            },2000);
        }
    }
    typeWriter();

});