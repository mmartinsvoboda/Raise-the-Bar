# sport_app_aws

Code sample application (platform independent)


The goal is to design and implement an easy mobile application that will record sport
performance results and store them in local storage (on device) and remote storage
(Firebase, custom back-end...). 


The user of the application should have possibility to choose
from local or remote storage.


Application should contain 2 parts:

● Form to enter the sport performance result (e.g. duration and distance for running)
(see Sport results form requirements)

● Listing of results (see Listing requirements)


You should design the user’s flow (navigation) in application to provide user experience as
good as possible. Implement navigation flow and provide us a description of flow and your
decisions.


Application should work in portrait and landscape modes.


It should be consistent in architecture of the app.


Sport results form requirements


Screen for entering the sport result should enable to:

● Enter the name of sport result

● Enter the place of sport result

● Duration of sport record

● Selection of storage - either local or remote.

● After the submit entry should be stored in selected storage and visible in listing


Listing requirements

Screen with listing should contain:

● Listing of entries by selection (all / local / remote)

● Different color for entries from local / remote storage


Additional extensions to show yourself are welcomed. Use the latest native frameworks,
libraries and technologies you know.


Additional Android requirements
● Do not use firebase SDK (or similar library) - We want to see some Rest API
/GraphQL client.
