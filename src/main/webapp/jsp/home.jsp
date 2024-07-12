<%@ page import="java.time.LocalDate" %>
<%
    //session check
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    //retrieve username
    String username = session.getAttribute("username").toString();
%>

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
    <input type="hidden" id="usernameHidden" name="usernameHidden" value="<%=username%>">
    <div class="logout-container">
        <button id="logout-button" onclick="logout()" title="Log Out"><!------- logout button --------->
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0z"/>
                <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"/>
            </svg>
        </button>
    </div>
    <div class="row justify-content-center align-items-center">
        <div class="col-lg-6 col-md-8 col-sm-10">
            <div class="welcome-text hidden text-center">
                <h1 class="display-4"><span id="type-writing"></span></h1> <!----display welcome message in typewriting effect--->

            </div>
        </div>
    </div>

    <button class="btn btn-primary" id="add-event-button" type="button" data-bs-toggle="modal" data-bs-target="#eventModal"><!----add event button--->
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
        </svg>
    </button>

    <div class="events-container" id="eventsContainer"><!------events display----->
        <div class="card event-item">
            <div class="event-date">Event Date</div>
            <div class="event-name">Event Name ~ Events did not load properly</div>
        </div>
    </div>

    <div class="row mt-5 position-absolute w-100" style="bottom: 0;"><!----slothy logo--->
        <div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1 col-sm-12 text-center">
            <img src="../images/slothyText.png" class="img-fluid" id="slothy-main" alt="Slothy Image">
            <div class="copyright">© 2024 3Des. All rights reserved.</div>
        </div>
    </div>
</main>

<div class="modal fade" id="eventModal" tabindex="-1" aria-labelledby="eventModalLabel" aria-hidden="true"><!-------event addition pop up--------->
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="eventModalLabel">Create New Event</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="eventForm">
                    <div class="mb-3">
                        <label for="eventName" class="form-label">Event Name</label>
                        <input type="text" class="form-control" id="eventName" name="eventName" required>
                    </div>
                    <div class="mb-3">
                        <label for="eventDate" class="form-label">Starting Date</label>
                        <input type="datetime-local" class="form-control" id="eventDate" name="eventDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="eventDuration" class="form-label">Duration (minutes)</label>
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
                    <div class="mb-3" id="participantUsernamesField" style="display: none;">
                        <label for="participantUsernames" class="form-label">Participant Usernames</label>
                        <input type="text" class="form-control" id="participantUsernames" name="participantUsernames" placeholder="Enter usernames separated by commas">
                    </div>
                    <button type="button" class="btn btn-primary" style="background: #927C67; border:none;" onclick="createNewEvent()">Create Event</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editEventModal" tabindex="-1" aria-labelledby="editEventModalLabel" aria-hidden="true"><!-------event edit pop up--------->
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editEventModalLabel">Edit Event</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="editEventForm">
                    <input type="hidden" id="editEventId" name="editEventId">
                    <div class="mb-3">
                        <label for="editEventName" class="form-label">Event Name</label>
                        <input type="text" class="form-control" id="editEventName" name="editEventName" required>
                    </div>
                    <div class="mb-3">
                        <label for="editEventDate" class="form-label">Starting Date</label>
                        <input type="datetime-local" class="form-control" id="editEventDate" name="editEventDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="editEventDuration" class="form-label">Duration (minutes)</label>
                        <input type="number" class="form-control" id="editEventDuration" name="editEventDuration" min="5" required>
                    </div>
                    <div class="mb-3">
                        <label for="editEventDescription" class="form-label">Description</label>
                        <input type="text" class="form-control" id="editEventDescription" name="editEventDescription" required>
                    </div>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="editEventIsPublic" name="editEventIsPublic">
                        <label class="form-check-label" for="editEventIsPublic">Public Event</label>
                    </div>
                    <div class="mb-3" id="editParticipantUsernamesField" style="display: none;">
                        <label for="editParticipantUsernames" class="form-label">Participant Usernames</label>
                        <input type="text" class="form-control" id="editParticipantUsernames" name="editParticipantUsernames" placeholder="Enter usernames separated by commas">
                    </div>
                    <button type="button" class="btn btn-primary" style="background: #927C67; border:none;" onclick="saveEventEdits()">Save Edits</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="eventCommentsModal" tabindex="-1" aria-labelledby="eventCommentsModalLabel" aria-hidden="true"><!-------event comments pop up--------->
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="eventCommentsModalLabel">Event Name</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="eventCommentForm">
                    <div class="mb-3">
                        <label for="eventCommentString" class="form-label">Add a Comment:</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="eventCommentString" name="eventCommentString" required>
                            <button class="btn btn-outline-secondary" id="add-comment-button" type="button">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
                                    <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576zm6.787-8.201L1.591 6.602l4.339 2.76z"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
                <div class="comments-container" id="commentsContainer">
                    <div class="card comment-item">
                        <div class="commenter-username">Commenter Username</div>
                        <div class="comment-text">Comment Text ~ Comments did not load properly </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="../scripting/home.js"></script>

<script>
    const username = $('#usernameHidden').val();
    const contextPath = '<%= request.getContextPath() %>';

    //clear the form fields
    function clearForm() {
        $('#eventForm').trigger('reset');
        $('#participantUsernamesField').hide();
        $('#eventIsPublic').prop('checked', false);
    }



    //creating new event with call to servlet
    function createNewEvent(){
        var eventName = $('#eventName').val();
        var eventDate = $('#eventDate').val();
        var eventDuration = $('#eventDuration').val();
        var eventDescription = $('#eventDescription').val();
        var eventUsername = $('#usernameHidden').val();
        var eventIsPublic = $('#eventIsPublic').is(':checked');
        var participantUsernames = eventIsPublic ? $('#participantUsernames').val() : null;

        console.log("Creating event with details:", {
            eventName, eventDate, eventDescription, eventUsername, eventIsPublic, participantUsernames
        });

        //asynchronous call to createEventServlet
        $.ajax({
            url: contextPath + '/create-event-servlet',
            type: 'POST',
            data: {
                eventName: eventName,
                eventDate: eventDate,
                eventDuration: eventDuration,
                eventDescription: eventDescription,
                creatorUsername: eventUsername,
                eventIsPublic: eventIsPublic,
                eventParticipants: participantUsernames
            },
            success: function() {
                //if event created, clear the modal and load the events again
                clearForm();
                loadEvents();

                //hide the modal automatically
                $('#eventModal').modal('hide');
            },
            error: function(xhr, status, error) {
                //display error in console if event wasnt created
                console.error('Error creating event:', error);
            }
        });
    }



    //load events function that sorts the events of a user
    function loadEvents(){
        //asynchronous call to loadEventServlet
        $.ajax({
            url: contextPath + '/load-events-servlet',
            type: 'GET',
            data: { username: username },
            success: function(response) {
                //if success, empty possible events and display them sorted
                console.log("Events loaded successfully:", response);
                $('#eventsContainer').empty();

                //sorting the array
                response.sort((a, b) => new Date(a.eventDate) - new Date(b.eventDate));

                //for each event, create an event div view
                response.forEach(function(event) {
                    let eventDate = new Date(event.eventDate);

                    let eventEndDate = new Date(eventDate.getTime() + event.eventDuration * 60 * 1000);

                    let formattedStartDate = eventDate.toLocaleString('en-GB', {
                        day: '2-digit',
                        month: '2-digit',
                        year: 'numeric',
                        hour: '2-digit',
                        minute: '2-digit'
                    }).replace(',', ' at');

                    let formattedEndDate = eventEndDate.toLocaleString('en-GB', {
                        day: '2-digit',
                        month: '2-digit',
                        year: 'numeric',
                        hour: '2-digit',
                        minute: '2-digit'
                    }).replace(',', ' at');

                    let duration = formattedStartDate + ' - ' + formattedEndDate;

                    let isPublic = event.isPublic ? "Public Event by " + event.creatorUsername : "Private Event";

                    if(event.creatorUsername === username){
                        $('#eventsContainer').append(
                            '<div class="card event-item" id="' + event.eventId + '">' +
                            '<div class="event-date">' + duration + '</div>' +
                            '<div class="event-name">' + event.eventName + '</div>' +
                            '<div class="event-description">' + event.eventDescription + '</div>' +
                            '<div class="event-isPublic">' + isPublic + '</div>' +
                            '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat comments-icon" viewBox="0 0 16 16 " onclick="showCommentsModal(' + event.eventId + ',\'' + event.eventName + '\')">' +
                            '<path d="M2.678 11.894a1 1 0 0 1 .287.801 11 11 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8 8 0 0 0 8 14c3.996 0 7-2.807 7-6s-3.004-6-7-6-7 2.808-7 6c0 1.468.617 2.83 1.678 3.894m-.493 3.905a22 22 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a10 10 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9 9 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105"/>'+
                            '</svg>'+
                            '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen edit-event-icon" viewBox="0 0 16 16" onclick="editEvent(' + event.eventId + ')">' +
                            '<path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001m-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708z"/>' +
                            '</svg>' +
                            '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash delete-event-icon" viewBox="0 0 16 16" onclick="deleteEvent(' + event.eventId + ')">' +
                            '<path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8z"/>' +
                            '</svg>' +
                            '</div>'
                        );
                    }else{
                        $('#eventsContainer').append(
                            '<div class="card event-item" id="' + event.eventId + '">' +
                            '<div class="event-date">' + duration + '</div>' +
                            '<div class="event-name">' + event.eventName + '</div>' +
                            '<div class="event-description">' + event.eventDescription + '</div>' +
                            '<div class="event-isPublic">' + isPublic + '</div>' +
                            '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat comments-icon-participant" viewBox="0 0 16 16 " onclick="showCommentsModal(' + event.eventId + ',\'' + event.eventName + '\')">' +
                            '<path d="M2.678 11.894a1 1 0 0 1 .287.801 11 11 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8 8 0 0 0 8 14c3.996 0 7-2.807 7-6s-3.004-6-7-6-7 2.808-7 6c0 1.468.617 2.83 1.678 3.894m-.493 3.905a22 22 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a10 10 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9 9 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105"/>'+
                            '</svg>' +
                            '</div>'
                        );
                    }
                });

                //add click event expand details
                $('.event-item').on('click', function() {
                    //check if the clicked event is already expanded
                    const isAlreadyExpanded = $(this).hasClass('expanded');

                    //remove the expanded class from all event items
                    $('.event-item').removeClass('expanded');

                    //if the clicked event was not already expanded, expand it
                    if (!isAlreadyExpanded) {
                        $(this).addClass('expanded');
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error('Error loading events:', error);
            }
        });
    }
    function editEvent(eventId) {

        console.log("Edit event with ID:", eventId);
        //load past values
        $.ajax({
            url: contextPath + '/load-events-servlet',
            type: 'GET',
            data: { username: username },
            success: function(response) {
                //if success, empty possible events and display them sorted
                console.log("Events loaded successfully:", response);
                const myevent = response.find(event => event.eventId === eventId)

                console.log("Event found", myevent);
                //save event id to hidden field
                $('#editEventId').val(eventId);
                //load event modal fields placeholders
                $('#editEventName').val(myevent.eventName);
                var eventDate = new Date(myevent.eventDate);

                // Convert to local date string suitable for datetime-local input
                var localDate = new Date(eventDate.getTime() - eventDate.getTimezoneOffset() * 60000).toISOString().slice(0, 16);
                $('#editEventDate').val(localDate);
                $('#editEventDuration').val(myevent.eventDuration);
                $('#editEventDescription').val(myevent.eventDescription);


                if (myevent.isPublic) {
                    console.log("event state:",myevent.isPublic)
                    $('#editEventIsPublic').prop('checked',true);
                    $('#editParticipantUsernamesField').show();

                    $.ajax({
                        url: contextPath + '/load-participants-servlet',
                        type: 'GET',
                        data: { eventId: eventId },
                        success: function(participants) {
                            console.log("Participants loaded successfully:", participants);

                            // Construct comma-separated string of usernames
                            let participantUsernames = participants.map(participant => participant.username).join(', ');

                            // Set value to editParticipantUsernames field
                            $('#editParticipantUsernames').val(participantUsernames);
                        },
                        error: function(xhr, status, error) {
                            console.error('Error loading participants:', error);
                        }
                    });
                } else { $('#editEventIsPublic').prop('checked', false);
                    $('#editParticipantUsernamesField').hide();
                    $('#editParticipantUsernames').val('');
                }
                $('#editEventForm').data('editing', true).data('eventId', eventId);
                $('#editEventModal').modal('show');
            }

        });


    }
    function showCommentsModal(eventId, eventName){
        console.log('Handle comments of event with id:', eventId);
        console.log('and name:', eventName);
        //load event title for modal title
        $('#eventCommentsModalLabel').text(eventName);

        //load comments
        $.ajax({
            url:contextPath+ '/load-comments-servlet',
            type: 'GET',
            data: {eventId: eventId},
            success: function(response){
                console.log("Comments loaded successfully:", response);
                $('#commentsContainer').empty();

                response.forEach(function(comment){
                    console.log("This is the username", comment.username);
                    $('#commentsContainer').append(
                        '<div class="card comment-item">'+
                        '<div class="commenter-username">'+comment.username+'</div>'+
                        '<div class="comment-text">'+ comment.comment +'</div>'+
                        '</div>'
                    );
                });
            },error: function(xhr, status, error) {
                console.error('Error loading comments:', error);
            }
        });
        //enable add comment
        $('#add-comment-button').off('click');
        $('#add-comment-button').on('click',function(){
            addComment(eventId);
        });
        $('#eventCommentsModal').modal('show');
    }

    function addComment(eventId){
        var comment=$('#eventCommentString').val();
        console.log("adding comment with details:",{username,comment,eventId});
        $.ajax({
            url: contextPath + '/add-comment-servlet',
            type: 'POST',
            data: {
                username:username,
                eventId:eventId,
                comment: comment
            },

            success: function() {
                //if event created, clear the modal and load the events again
                $('#eventCommentString').val('');
                $('#eventCommentsModal').modal('hide');
            },
            error: function(xhr, status, error) {
                //display error in console if event wasnt created
                console.error('Error creating event:', error);
            }
        });
    }

    function saveEventEdits(){
        var eventId=$('#editEventId').val();
        var eventName = $('#editEventName').val();
        var eventDate = $('#editEventDate').val();
        var eventDuration = $('#editEventDuration').val();
        var eventDescription = $('#editEventDescription').val();
        var eventUsername = $('#usernameHidden').val();
        var eventIsPublic = $('#editEventIsPublic').is(':checked');
        var participantUsernames = eventIsPublic ? $('#editParticipantUsernames').val() : null;

        console.log("Editing event with new details:", {
            eventId, eventName, eventDate, eventDescription, eventUsername, eventIsPublic, participantUsernames
        });


        //asynchronous call to createEventServlet
        $.ajax({
            url: contextPath + '/edit-event-servlet',
            type: 'POST',
            data: {
                eventId: eventId,
                eventName: eventName,
                eventDate: eventDate,
                eventDuration: eventDuration,
                eventDescription: eventDescription,
                creatorUsername: eventUsername,
                eventIsPublic: eventIsPublic,
                eventParticipants: participantUsernames
            },
            success: function() {
                //if event created, clear the modal and load the events again
                console.log('event details edited successfully');
                clearForm();
                loadEvents();

                //hide the modal automatically
                $('#editEventModal').modal('hide');
            },
            error: function(xhr, status, error) {
                //display error in console if event wasnt created
                console.error('Error editing event:', error);
            }
        });

    }


    function deleteEvent(eventId) {
        console.log("Delete event with ID:", eventId);

        //asynchronous call to deleteEventServlet
        $.ajax({
            url: contextPath + '/delete-event-servlet',
            type: 'POST',
            data: { eventId: eventId, username: username },
            success: function(response) {
                //if success, display response message
                console.log("Events deleted successfully:", response);
                loadEvents();

            },
            error: function(xhr, status, error) {
                console.error('Error loading events:', error);
            }
        });
    }






    //when document is ready
    $(document).ready(function () {

        window.onbeforeunload = function () {
            clearForm();
        };

        //clear the modal
        $('#eventModal').on('hidden.bs.modal', function () {
            clearForm();
        });

        //load the events of the user
        loadEvents();

        //if checkbox isPublic is clicked, show and hide participants accordingly
        $('#eventIsPublic').change(function () {
            if (this.checked) {
                $('#participantUsernamesField').show();
            } else {
                $('#participantUsernamesField').hide();
                $('#participantUsernames').val('');
            }
        });
        $('#editEventIsPublic').change(function () {
            if (this.checked) {
                $('#editParticipantUsernamesField').show();
            } else {
                $('#editParticipantUsernamesField').hide();
                $('#editParticipantUsernames').val('');
            }
        });
    });

    function logout() {
        sessionStorage.clear();//clear session storage
        window.location.href = '<%= request.getContextPath() %>/index.jsp';// redirect to index.jsp
    }

</script>


</body>
</html>
