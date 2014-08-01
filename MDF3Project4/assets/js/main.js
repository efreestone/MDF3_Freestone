/*
Elijah Freestone
MDF3 1407
Project 4
7-30-14
*/

window.addEventListener("DOMContentLoaded", function() {
    //getElementById Function
    function $(x) {
        var theElement = document.getElementById(x);
        return theElement;
    }
    
    //Start functions to grab event info. Triggered on button click
    function saveClicked() {
    	Android.test();
        getSelectedRadio();
        grabEvent();
    };
    
    //Grab details from inputs and send to Native
    function grabEvent() {
    var eventTitle = "Event Title: " + $("eventTitle").value;
    var eventDate = "Event Date: " + $("eventDate").value;
    var eventType = "Event Type: " + $("eventType").value;
    //getSelectedRadio()
    var eventDetails = "Event Details: " + $("eventDetails").value;
		    
		Android.saveEvent(eventTitle, eventDate, eventType, attendValue, eventDetails);
    }
    
    //Find value of selected radio button.
    function getSelectedRadio() {
        var radios = document.forms[0].attend;
        for(var i=0; i<radios.length; i++) {
            if(radios[i].checked) {
                attendValue = "Attendance Required?: " + radios[i].value;
            }
        }
    }; 
    
    //Variable defaults
    var eventTypes = ["--Choose An Event Type--", "Birthday", "Anniversary", "Other"],
        attendValue;
    
    //Event listener 
    var save = $("submit");
    save.addEventListener("click", saveClicked);
    
});

