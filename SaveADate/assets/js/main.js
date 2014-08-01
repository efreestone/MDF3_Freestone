/*
Elijah Freestone
MDF3 1407
Project 4
7-30-14
*/

window.addEventListener("DOMContentLoaded", function() {

	//getElementById Function
    function gE(x) {
        var theElement = document.getElementById(x);
        return theElement;
    }
    
    //Start functions to grab event info. Triggered on button click
    function saveClicked() {
        getSelectedRadio();
        grabEvent();
    };
    
    //Grab details from inputs and send to Native
    function grabEvent() {
    	var eventTitle = "Event Title: " + gE("eventTitle").value;
    	var eventDate = "Event Date: " + gE("eventDate").value;
    	var eventType = "Event Type: " + gE("eventType").value;
    	//getSelectedRadio()
    	var eventDetails = "Event Details: " + gE("eventDetails").value;
		    
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
    
    //Validate entry fields
    function validate(e) {
    	//Define elements we want to check
    	var getEventType = gE("eventType");
    	var getEventDate = gE("eventDate");
    	var getEventTitle = gE("eventTitle");
    	
    	//Reset error messages
    	errMsg.innerHTML = "";
    	
    	//Get error messages
    	var messageAry = [];
    	
    	//Event info validation
	    if(getEventTitle.value === "") {
		    var evInfoError = "Please add a brief description of the event";
	    	getEventTitle.style.border = "1px solid red";
	    	messageAry.push(evInfoError);
	    }
	    
	    //Event date validation
	    if(getEventDate.value === "") {
		    var evDateError = "Please choose a date for the event.";
	    	getEventDate.style.border = "1px solid red";
	    	messageAry.push(evDateError);
	    }
	    
    	//Event validation
    	if(getEventType.value === "") {
	    	var eventsError = "Please choose an event type.";
	    	getEventType.style.border = "1px solid red";
	    	messageAry.push(eventsError);
    	}
	    	
	    //If there were errors, display the on the screen
	    if(messageAry.length >= 1) {
		    for(var i=0, j=messageAry.length; i < j; i++) {
			    var txt = document.createElement("li");
			    txt.innerHTML = messageAry[i];
			    errMsg.appendChild(txt);
		    }
		    e.preventDefault();
		    return false;
	    }else{
		    //If all is ok trigger the cascade to grab input values and send to native
		    saveClicked();
		 
	    }	   
    };
    
    //Variable defaults
    var eventTypes = ["--Choose An Event Type--", "Birthday", "Anniversary", "Other"],
        attendValue,
        errMsg = gE("errors");
    
    //Event listener 
    var save = gE("submit");
    save.addEventListener("click", validate);
    
});

