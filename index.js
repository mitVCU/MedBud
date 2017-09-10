'use strict';
const Alexa = require('alexa-sdk');
const AWS = require('aws-sdk');

//=========================================================================================================================================
//TODO: The items below this comment need your attention.
//=========================================================================================================================================

const APP_ID = undefined;

const SKILL_NAME = 'Space Facts';
const GET_FACT_MESSAGE = "Here's your fact: ";
const HELP_MESSAGE = 'You can say tell me a space fact, or, you can say exit... What can I help you with?';
const HELP_REPROMPT = 'What can I help you with?';
const STOP_MESSAGE = 'Goodbye!';
const CONDITION_LIST = ['ibuprofen', 'sovaldi', 'abilify', 'januvia', 
'spiriva', 'lyrica', 'vyvanse', 'lantus', 'advil', 'advair diskus', 'nexium',
'crestor', 'synthroid', 'blue', 'red', 'humera'];

const docClient = new AWS.DynamoDB.DocumentClient();

const handlers = {
    'LaunchRequest': function () {
        this.emit('GetNewFactIntent');
    },
    'MedBuddyDefault': function () {
        var currentDate = new Date();
    var currentDateString = currentDate.toString();
    
    var params = {
        TableName:"Time",
        Key:{
            "ID": 1
        },
        UpdateExpression: "set lastTime = :r",
        ExpressionAttributeValues:{
            ":r": currentDateString
        },
        ReturnValues: "UPDATED_NEW"
    };

    console.log("Updating the item...");
    docClient.update(params, function(err, data) {
        if (err) {
           console.error("Unable to update item. Error JSON:", JSON.stringify(err, null, 2));
        } else {
           console.log("UpdateItem succeeded:", JSON.stringify(data, null, 2));
        }
    });
        this.response.speak('done');
        this.emit(':responseReady');
    },
    'SetMedicationFrequencyIntent': function () {
        
        if (this.event.request.dialogState == "STARTED" || this.event.request.dialogState == "IN_PROGRESS"){
            this.context.succeed({
                "response": {
                    "directives": [
                        {
                            "type": "Dialog.Delegate"
                        }
                    ],
                    "shouldEndSession": false
                },
                "sessionAttributes": {}
            });
        }
        
            var drugName = this.event.request.intent.slots.drugName.value.toLowerCase();
            var drugFrequency = this.event.request.intent.slots.drugFrequency.value;
            
    var params = {
        TableName:"MedicationInformation",
        Key:{
            "MedicationName": drugName
        },
        UpdateExpression: "set MedicationFrequency = :r",
        ExpressionAttributeValues:{
            ":r": drugFrequency
        },
        ReturnValues: "UPDATED_NEW"
    };

    console.log("Updating the item...");
    docClient.update(params, function(err, data) {
        if (err) {
           console.error("Unable to update item. Error JSON:", JSON.stringify(err, null, 2));
        } else {
           console.log("UpdateItem succeeded:", JSON.stringify(data, null, 2));
        }
    });
            
            this.response.speak("Okay, I updated " + drugName.toString() + " to " + drugFrequency.toString()) + " a day";
            this.emit(':responseReady');
            
            /*var params = {
                TableName : "MedicationInformation",
                KeyConditionExpression: "MedicationName = :name ",
                ExpressionAttributeNames:{
                    
                },
                    ExpressionAttributeValues: {
                    ":name":drugName
                }
            };
            
            docClient.get(params, (err, data) => {
                if (err) {
                    console.error("Unable to read item. Error JSON:", JSON.stringify(err, null, 2));
                } else {
                    console.log("GetItem succeeded:", JSON.stringify(data, null, 2));

                    var params = {
                TableName: "MedicationInformation",
                Item: {
                    MedicationName : drugName,
                    MedicationQuantity : data.Item.MedicationQuantity,
                    MedicationTime : data.Item.MedicationQuantity,
                    MedicationFrequency : drugFrequency
                }
            };

            docClient.put(params, function(err, data) {
                if(err) {}
                else {}
            }); 

                }
            });*/
            
        
        
    },
    'AddNewMedicine': function () {
    
        if (this.event.request.dialogState == "STARTED" || this.event.request.dialogState == "IN_PROGRESS"){
            this.context.succeed({
                "response": {
                    "directives": [
                        {
                            "type": "Dialog.Delegate"
                        }
                    ],
                    "shouldEndSession": false
                },
                "sessionAttributes": {}
            });
        } else {
            
            var drugName = this.event.request.intent.slots.drugToAdd.value.toLowerCase();
            var drugQuantity = this.event.request.intent.slots.drugQuantity.value.toLowerCase();
            var drugTime = this.event.request.intent.slots.drugTime.value;

            var params = {
                TableName: "MedicationInformation",
                Item: {
                    MedicationName : drugName,
                    MedicationQuantity : drugQuantity,
                    MedicationTime : drugTime,
                    TakenToday  : false,
                    MedicationFrequency : 1,
                    TimesTakenToday : 0
                }
            };

            docClient.put(params, function(err, data) {
                if(err) {}
                else {}
            }); 
            
            this.response.speak("Okay, I added " + drugName.toString() + " to your list.");
            this.emit(':responseReady');
        }

    },
    'TakenMedicine': function () {
        if (this.event.request.dialogState == "STARTED" || this.event.request.dialogState == "IN_PROGRESS"){
            this.context.succeed({
                "response": {
                    "directives": [
                        {
                            "type": "Dialog.Delegate"
                        }
                    ],
                    "shouldEndSession": false
                },
                "sessionAttributes": {}
            });
        }
        
        var drugName = this.event.request.intent.slots.drugName.value.toLowerCase();
        var date = new Date();
        var dateString = date.toString();
        var current_hours = date.getHours();
        
        var params = {
            TableName:"MedicationInformation",
            Key:{
                "MedicationName": drugName
            },
            UpdateExpression: "set TakenToday = :r, lastTime = :d",
            ExpressionAttributeValues:{
                ":r": true,
                ":d": dateString
            },
            ReturnValues: "UPDATED_NEW"
        };

        console.log("Updating the item...");
        docClient.update(params, function(err, data) {
            if (err) {
                console.error("Unable to update item. Error JSON:", JSON.stringify(err, null, 2));
            } else {
               console.log("UpdateItem succeeded:", JSON.stringify(data, null, 2));
            }
        });
        
        function update(p) {
            console.log("Updating the item...");
            docClient.update(p, function(err, data) {
                if (err) {
                    console.error("Unable to update item. Error JSON:", JSON.stringify(err, null, 2));
                } else {
                   console.log("UpdateItem succeeded:", JSON.stringify(data, null, 2));
                }
            });
        }
        
        var params2 = {
            TableName : "MedicationInformation",
            Key : {
                "MedicationName": drugName
            }
        };
        var thisObject = this;
        docClient.get(params2, function(err, data) {
            if (err) {
                console.error("Unable to read item. Error JSON:", JSON.stringify(err, null, 2));
            } else {
                console.log("GetItem succeeded:", JSON.stringify(data, null, 2));
                var q = data.Item.MedicationQuantity - 1;
                var ttt = data.Item.TimesTakenToday + 1;
                params = {
                    TableName:"MedicationInformation",
                    Key:{
                        "MedicationName": drugName
                    },
                    UpdateExpression: "set MedicationQuantity = :q, TimesTakenToday = :t",
                    ExpressionAttributeValues:{
                        ":q": q,
                        ":t": ttt
                    },
                    ReturnValues: "UPDATED_NEW"
                };
                update(params);
                var speechOut = "Okay, you just took " + drugName;
                if(ttt >= data.Item.MedicationFrequency) {
                    speechOut += ("You are done with " + drugName + " for the day.");
                }
                thisObject.response.speak(speechOut);
                thisObject.emit(':responseReady');
            }
            
        });
        //this.response.speak("hello");
        //this.emit(':responseReady');
    
        
        /*var params = {
            TableName : "Time",
            Key : {
                ID: 1 
            }
        };
        
        var lastDate;
        var number = 5;
        
 
        docClient.get(params, function(err, data) {
            if (err) {
                console.error("Unable to read item. Error JSON:", JSON.stringify(err, null, 2));
            } else {
                console.log("GetItem succeeded:", JSON.stringify(data, null, 2));
                console.log("hello" + data.Item.lastTime);
                lastDate = data.Item.lastTime.toString();
                number = 10;
                console.log("inside" + number);
                this.response.speak("the last date is" + lastDate + ". Done.");
                this.emit(':responseReady');
            }
        });*/
        
        
    },
    'Report' : function () {
        
    },
    'Refresh' : function () {
     
    
        function update(params1) {
        docClient.update(params, function(err, data) {
        if (err) {
           console.error("Unable to update item. Error JSON:", JSON.stringify(err, null, 2));
        } else {
           console.log("UpdateItem succeeded:", JSON.stringify(data, null, 2));
        }
    });
    }
    
        for (var i = 0, len = CONDITION_LIST.length; i < len; i++) {
            var condition = CONDITION_LIST[i];
            
            var params = {
        TableName:"MedicationInformation",
        Key:{
            "MedicationName": condition
        },
        UpdateExpression: "set TakenToday = :r, TimesTakenToday = :t",
        ExpressionAttributeValues:{
            ":r": false,
            ":t": 0
        },
        ReturnValues: "UPDATED_NEW"
    };
    update(params);

    console.log("Updating the item...");
        }
        this.response.speak("okay done");
        this.emit(":responseReady");
        
    },
    'HaveITakenIntent': function () {
        
        var drugName = this.event.request.intent.slots.drugName.value.toLowerCase();

        var params = {
            TableName : "MedicationInformation",
            Key : {
                "MedicationName": drugName
            }
        };
        var thisObject = this;
        var data1 = docClient.get(params, function(err, data) {
            if (err) {
                console.error("Unable to read item. Error JSON:", JSON.stringify(err, null, 2));
            } else {
                console.log("GetItem succeeded:", JSON.stringify(data, null, 2));
                var taken = data.Item.TimesTakenToday >= data.Item.MedicationFrequency;
                var takenString = taken ? "" : " not";
                thisObject.response.speak("You have" + takenString + " finished " + drugName + " today");
                //thisObject.response.speak("Hello there yo");
                thisObject.emit(':responseReady');
                //this.emit(':responseReady');
            }
        });
        //this.response.speak("Hello there yo");
        //this.emit(':responseReady');
    },
    'RefreshOne': function () {
        var drugName = this.event.request.intent.slots.drugName.value.toLowerCase();
        var params = {
            TableName:"MedicationInformation",
            Key:{
                "MedicationName": drugName
            },
            UpdateExpression: "set TakenToday = :f, TimesTakenToday = :t",
            ExpressionAttributeValues:{
                ":f" : false,
                ":t" : 0
            },
            ReturnValues: "UPDATED_NEW"
        };

        console.log("Updating the item...");
        docClient.update(params, function(err, data) {
            if (err) {
                console.error("Unable to update item. Error JSON:", JSON.stringify(err, null, 2));
            } else {
               console.log("UpdateItem succeeded:", JSON.stringify(data, null, 2));
            }
        });
        
        this.response.speak("Okay, done");
        this.emit(":responseReady");
    },
    'AMAZON.HelpIntent': function () {
        const speechOutput = HELP_MESSAGE;
        const reprompt = HELP_REPROMPT;

        this.response.speak(speechOutput).listen(reprompt);
        this.emit(':responseReady');
    },
    'AMAZON.CancelIntent': function () {
        this.response.speak(STOP_MESSAGE);
        this.emit(':responseReady');
    },
    'AMAZON.StopIntent': function () {
        this.response.speak(STOP_MESSAGE);
        this.emit(':responseReady');
    },
};

exports.handler = function (event, context, callback) {
    const alexa = Alexa.handler(event, context, callback);
    alexa.APP_ID = APP_ID;
    alexa.registerHandlers(handlers);
    alexa.execute();
};  