<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Slothy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="../styling/home.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@200&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@400&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="../images/slothyWaving.png">
</head>
<body>
<main class="container-fluid">
    <div class="row justify-content-center align-items-center vh-100">
        <div class="col-lg-6 col-md-8 col-sm-10">
            <input type="hidden" id="usernameHidden" value="<%=session.getAttribute("username")%>">
            <div class="welcome-text hidden text-center">
                <h1 class="display-4"><span id="type-writing"></span></h1>
            </div>
        </div>
    </div>
    <button class="btn btn-primary" id="add-event-button" type="submit">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
        </svg>
    </button>
    <div class="events-container">
        <div class="card event-item">Go shopping</div>
        <div class="card event-item">Team Call</div>
        <div class="card event-item">Take out Trash</div>
        <div class="card event-item">See despoinio</div>
        <div class="card event-item">Walk the dog</div>
        <div class="card event-item">Climb Tree</div>
        <div class="card event-item">Visit paradise</div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="../scripting/home.js"></script>

</body>
</html>
