# <img align="left" src="assets/appIcon.svg" alt="image" width="45" height="45"/>MiddenTest
This is a technical test for Mind Den.

<p align="center">
  <kbd hspace="20">
   <img src="assets/ListadiContactosM.png" width="200" >
  </kbd>
  <kbd hspace="20">
   <img src="assets/DetalleUsuarioM.png" width="200">
  </kbd>
</p>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#explanation">Explanation</a>
    </li>
    <li>
      <a href="#app-structure">App Structure</a>
    <li>
      <a href="#app-usage">App Usage</a>
    </li>
  </ol>      
</details>

## Explanation
The application consist of two screens, one shows a list of users and the other the detail of users.
The information comes from https://randomuser.me/api/, a Rest API service which generates users. For this test, I used the seed "Midden".
There is a function which ensures there isn't duplicate data showing in the app.
There is also a search feature which allows the user to search by name or email for a concrete user.
You can scroll indefinitely on the app until the information is completely loaded.

## App structure
The app is made using Kotlin and Jetpack Compose. It's structured the following way:

 - There is a folder called "compose" in which there are all the UI
   components. They are divided in "screens" and "components" which
   compose those screens. All the App is done in UICompose and depending
   on which screen is visible the navigator goes to one screen or the
   other. There is also a Viewhelper taking care of functions used in
   more than one screen or big functions which doesn't need to be in one
   screen or component.
   
- In "core" folder you can find components common for all the app like
   modules for Koin injection dependencies or constants.
   
- In "data/models", you can find the models used for this app,
   LoadingState for processing the loading states from the API and
   UserProfile which has all the data classes needed for the information
   coming from the API.
   
- In "network", you can find all the components related with the API
   connection using Retrofit. The API Service, the repository and the
   UseCase. You can find their respective tests in the test section of
   the app.
- This part of the app communicates with the UI using a
   viewmodel which is located in "viewmodels".
  <br></br><p align="center"><img src="assets/middenTestScreenCapture1.PNG" width="200"></p>

## App usage

When you first open the app, the first screen you see it's "Contact List Screen" in which you can see all the users given by the API. In there, you can press the three dots icon in the top left of the screen, and you will open a dropdown menu in which you can press "Search" to open the Search bar and write a name or an email to search for a user. Given how the API it's implemented, you can only search for a user already in the list downloaded.
From there, you can go to "User Info Screen" by pressing on a row of the list, a user, or a given result in the search.
<p align="center">
  <kbd hspace="20">
   <img src="assets/middenTestScreenCapture4.jpeg" width="200" >
  </kbd>
  <kbd hspace="20">
   <img src="assets/middenTestScreenCapture3.jpeg" width="200">
  </kbd>
 <kbd hspace="20">
   <img src="assets/middenTestScreenCapture5.jpeg" width="200">
  </kbd>
</p>

In  "User Info Screen" you can see more information about the user like their gender or their location. Given how Google Maps is implemented, it's using the location given by the API, not the actual street given by the user. Nevertheless, there is a marker with their address information, just not the actual coordinates.
  <br></br>
  <p align="center">
   <kbd>
   <img src="assets/middenTestScreenCapture2.jpeg" width="200">
   </kbd>
  </p>

There is also a dark mode implemented, which appears at night if the phone has dark mode. 
<br></br>
<p align="center">
 <kbd>
 <img src="assets/middenTestScreenCapture6.jpeg" width="200">
 </kbd>
</p>

If there isn't network connection or the API returns error, there is a screen for this case which can reload the information if pulled down.

<br></br>
<p align="center">
 <kbd>
 <img src="assets/middenTestScreenCapture7.jpeg" width="200">
 </kbd>
</p>
