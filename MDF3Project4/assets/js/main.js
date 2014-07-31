/*
Elijah Freestone
MDF3 1407
Project 4
7-30-14
*/

$('#index').on('pageinit', function(){
	var myForm = $("#eventForm"),
		popErrors = $('#popErrors');
			
		//Validate the event form
	    myForm.validate({
		invalidHandler: function(form, validator) {
			//Pop up error dialog box
			popErrors.click(); 
			var html = '';
			for(var key in validator.submitted) {
				//Search for required elements not filled out
				var label = $('label[for^="'+ key +'"]') 
				var legend = label.closest('fieldset').find('ui-controlgroup-label');
				var fieldName = legend.length ? legend.text() : label.text();
				html += '<li>'+ fieldName +'</li>';
			};
			//Creat pop up error text
			$("#formErrors ul").html(html);
		},
		submitHandler: function() {
			var data = myForm.serializeArray();
		}
	});
	
	//getElementById Function
    function gE(x) {
        var theElement = document.getElementById(x);
        return theElement;
    };
    
    //Find value of selected radio button.
    function getSelectedRadio() {
        var radios = document.forms[0].attend;
        for(var i=0; i<radios.length; i++) {
            if(radios[i].checked) {
                attendValue = radios[i].value;
            }
        }
    };
    
    //To hide or show input fields and controls
    function toggleControls(n) {
        switch(n) {
            case "on":
                gE("eventForm").style.display = "none";
                gE("clearData").style.display = "inline";
                gE("displayData").style.display = "none";
                gE("addNew").style.display = "inline";
                break;
            case "off":
                gE("eventForm").style.display = "block";
                gE("clearData").style.display = "inline";
                gE("displayData").style.display = "inline";
                gE("addNew").style.display = "none";
                gE("items").style.display = "none";
                break;
            default:
                return false;
        }    
    };
    
    //Validate entry fields
    function validate(e) {
    	//Define elements we want to check
    	var getEvents = gE("events");
    	var getEvdate = gE("evdate");
    	var getEvinfo = gE("evinfo");
    	
    	//Reset error messages
    	errMsg.innerHTML = "";
    	
    	//Get error messages
    	var messageAry = [];
    	//Event validation
    	if(getEvents.value === "--Choose An Event Type--") {
	    	var eventsError = "Please choose an event type.";
	    	getEvents.style.border = "1px solid red";
	    	messageAry.push(eventsError);
    	}
	    
	    //Event date validation
	    if(getEvdate.value === "") {
		    var evDateError = "Please choose a date for the event.";
	    	getEvdate.style.border = "1px solid red";
	    	messageAry.push(evDateError);
	    }
	    
	    //Event info validation
	    if(getEvinfo.value === "") {
		    var evInfoError = "Please add a brief description of the event";
	    	getEvinfo.style.border = "1px solid red";
	    	messageAry.push(evInfoError);
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
		    //If all is ok, save our data! Send the key value that came form the editData function.
		    //Remember this key value was passed through the editSubmit event listener as a property
		    //saveData(this.key);
	    }	   
    };
    
    //Variable defaults
    var eventTypes = ["--Choose An Event Type--", "Anniversary", "Appointment", "Birthday", "Meeting", "Other"],
        attendValue,
        errMsg = gE("errors");
	
});	