<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Slothy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="styling/index.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@200&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@400&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="images/slothyWaving.png">
</head>
<body>
    <main class="container main-container">
        <div id="error-message-bubble" class="error-message-bubble">Oops! Username or password incorrect.</div>
        <div class="welcome-text">
            <h1 class="display-4">Welcome Back!</h1>
            <h2 class="display-5">Login</h2>
        </div>
        <form id="login-form" method="post" action="login-servlet" class="mt-5 text-center">
            <div class="mb-4">
                <input type="text" id="usernameField" name="usernameField" class="form-control" placeholder="username">
            </div>
            <div class="mb-4">
                <input type="password" id="passwordField" name="passwordField" class="form-control" placeholder="password">
            </div>
            <button type="submit" class="arrow-circle-btn" id="submitBtn">
                <span class="arrow"></span>
            </button>
        </form>
        <div class="row mt-5">
            <div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1 col-sm-12">
                <img src="images/slothyWaving.png" id="slothy-waving" class="img-fluid" alt="Slothy Image">
                <p class="text-center mt-3">Don't have an account? <a href="jsp/register.jsp" style="color: #55473B">Register</a></p>
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="scripting/index.js"></script>
</body>
</html>
