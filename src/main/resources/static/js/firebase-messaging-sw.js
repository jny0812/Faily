importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-messaging.js');

// Initialize Firebase
let firebaseConfig = {
    apiKey: "AIzaSyB5oa7FwMPftevn7dCFHAAb76SRoEsB5NQ",
    authDomain: "fb-faily.firebaseapp.com",
    projectId: "fb-faily",
    storageBucket: "fb-faily.appspot.com",
    messagingSenderId: "279539279679",
    appId: "1:279539279679:web:909aabd3e3299c734bd58a",
    measurementId: "G-JPN40GMZ0G"
};
firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();