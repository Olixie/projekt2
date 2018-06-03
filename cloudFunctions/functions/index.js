const functions = require('firebase-functions');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
//import firebase functions modules
const functions = require('firebase-functions');
//import admin module
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);


// Listens for new messages added to messages/:pushId
exports.pushNotification = functions.database.ref('/wiadomosci/{pushId}').onWrite( (change,context) => {

    console.log('Push notification event triggered');

    //  Grab the current value of what was written to the Realtime Database.
    //console.log(event.data.val());
    //var wiad = event.data.val();
    const message = change.after.val();


   

  // Create a notification
    const payload = {
        notification: {
            title: message.tytulWiad,
            body: message.trescWiad,
            sound: "default"
        },
    };

  //Create an options object that contains the time to live for the notification and the priority
    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


    return admin.messaging().sendToTopic("pushNotifications", payload, options);
});