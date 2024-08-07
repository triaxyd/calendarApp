
document.getElementById('slothy-waving').setAttribute('draggable', 'false');

// show the error message bubble
function showErrorBubble() {
    let bubble = document.getElementById("error-message-bubble");
    bubble.style.opacity = 1; // Set opacity to 1 to make the bubble visible
    setTimeout(function() {
        bubble.style.opacity = 0; // Fade out the bubble after 4 seconds
    }, 2000);
}

// test form validation
document.getElementById('login-form').addEventListener('submit', function(event) {
    let isValid = true;
    let usernameField = document.getElementById("usernameField").value;
    let passwordField = document.getElementById("passwordField").value;


    if (usernameField.trim() === '' || usernameField.length < 5 || usernameField.length > 12) {
        isValid = false;
    }


    if (passwordField.trim() === '' || passwordField.length < 5 || passwordField.length > 20) {
        isValid = false;
    }

    if (!isValid) {
        showErrorBubble();
        event.preventDefault();
    }

});






