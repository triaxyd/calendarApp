
document.getElementById('slothy-main').setAttribute('draggable', 'false');

// show the error message bubble
function showErrorBubble() {
    let bubble = document.getElementById("error-message-bubble");
    bubble.style.opacity = 1;
    setTimeout(function() {
        bubble.style.opacity = 0;
    }, 2000);
}

// test form validation
document.getElementById('register-form').addEventListener('submit', function(event) {
    let isValid = true;
    let usernameField = document.getElementById("usernameField").value;
    let passwordField = document.getElementById("passwordField").value;
    let emailField = document.getElementById("ageField").value;


    if (usernameField.trim() === '' || usernameField.length < 5 || usernameField.length > 12) {
        isValid = false;
    }


    if (passwordField.trim() === '' || passwordField.length < 5 || usernameField.length > 20) {
        isValid = false;
    }

    if (emailField.trim() === '' || emailField < 10) {
        isValid = false;
    }

    if (!isValid) {
        showErrorBubble();
        event.preventDefault();
    }
});