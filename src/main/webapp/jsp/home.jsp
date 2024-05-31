<%@ page import="java.time.LocalDate" %>
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
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
<main class="container-fluid">
    <input type="hidden" id="usernameHidden" value="<%=session.getAttribute("username")%>">
    <div class="row justify-content-center align-items-center">
        <div class="col-lg-6 col-md-8 col-sm-10">
            <div class="welcome-text hidden text-center">
                <h1 class="display-4"><span id="type-writing"></span></h1>
            </div>
        </div>
    </div>

    <button class="btn btn-primary" id="add-event-button" type="button" data-bs-toggle="modal" data-bs-target="#eventModal">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
        </svg>
    </button>

    <div class="events-container" id="eventsContainer">
        <div class="card event-item">
            <div class="event-date">2024-06-01</div>
            <div class="event-name">Go shopping</div>
        </div>
        <div class="card event-item">
            <div class="event-date">2024-06-02</div>
            <div class="event-name">Team Call</div>
        </div>
        <div class="card event-item">
            <div class="event-date">2024-06-03</div>
            <div class="event-name">Take out Trash</div>
        </div>
        <div class="card event-item">
            <div class="event-date">2024-06-04</div>
            <div class="event-name">See despoinio</div>
        </div>
        <div class="card event-item">
            <div class="event-date">2024-06-05</div>
            <div class="event-name">Walk the dog</div>
        </div>
        <div class="card event-item">
            <div class="event-date">2024-06-06</div>
            <div class="event-name">Climb Tree</div>
        </div>
        <div class="card event-item">
            <div class="event-date">2024-06-07</div>
            <div class="event-name">Visit paradise</div>
        </div>
    </div>

    <div class="row mt-5 position-absolute w-100" style="bottom: 0;">
        <div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1 col-sm-12 text-center">
            <img src="../images/slothyText.png" class="img-fluid" id="slothy-main" alt="Slothy Image">
        </div>
    </div>
</main>
<div class="modal fade" id="eventModal" tabindex="-1" aria-labelledby="eventModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="eventModalLabel">Create New Event</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="eventForm">
                    <div class="mb-3">
                        <label for="eventId" class="form-label">Event ID</label>
                        <input type="number" class="form-control" id="eventId" name="eventId" min="0" required>
                    </div>
                    <div class="mb-3">
                        <label for="eventName" class="form-label">Event Name</label>
                        <input type="text" class="form-control" id="eventName" name="eventName" required>
                    </div>
                    <div class="mb-3">
                        <label for="eventDate" class="form-label">Date</label>
                        <input type="datetime-local" class="form-control" id="eventDate" name="eventDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="eventDuration" class="form-label">Duration</label>
                        <input type="number" class="form-control" id="eventDuration" name="eventDuration" min="5" required>
                    </div>
                    <div class="mb-3">
                        <label for="eventDescription" class="form-label">Description</label>
                        <input type="text" class="form-control" id="eventDescription" name="eventDescription" required>
                    </div>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="eventIsPublic" name="eventIsPublic">
                        <label class="form-check-label" for="eventIsPublic">Public Event</label>
                    </div>
                    <button type="button" class="btn btn-primary" style="background: #927C67; border:none;" onclick="createEvent()">Create Event</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="../scripting/home.js"></script>
<script>
    $(document).ready(function () {
        window.createEvent = function () {
            var eventId = $('#eventId').val();
            var eventName = $('#eventName').val();
            var eventDate = $('#eventDate').val();
            var eventDescription = $('#eventDescription').val();
            var eventUsername = $('#usernameHidden').val();
            var eventIsPublic = $('#eventIsPublic').val();

            $.ajax({
                url: '/createEvent',
                type: 'POST',
                data: {
                    eventName: eventName,
                    eventDate: eventDate
                },
                success: function(response) {
                    $('#eventsContainer').append(
                        '<div class="card event-item">' +
                        '<div class="event-date">' + response.eventDate + '</div>' +
                        '<div class="event-name">' + response.eventName + '</div>' +
                        '</div>'
                    );

                    $('#eventModal').modal('hide');
                },
                error: function(xhr, status, error) {
                    console.error('Error creating event:', error);
                }
            });
        };
    });
</script>

</body>
</html>
