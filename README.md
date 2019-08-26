# Celebrity App

This application uses SQLite and Content Providers to insert, update, delete and query information about celebrities.
Additionally, fragments are being used to utilize navigation drawers.

## Celebrities Fragment

This fragment uses a Recycler View to display a list of celebrities that exit in the database. When the user accesses the fragment via the "All Celebrities" button, the content provider queries all the information in the database and displays a list of the celebrities' names.

If the user accesses this fragment via the "Favorites" page, the content provider selects the celebrities that have been listed as favorites from the database and shows them in the Recycler view. Equivalently, when the user accessses the Fragment from the Industry page, the content provider selects selebrities whose industry is the one that the user picked, and displays only those on the screen.

Within this activity the user may also favorite or unfavorite a celebrity, by clicking on the heart button. The heart changes from white to red, when a celebrity is favorited.
Additionally, they may perform a long click on one of the celebrities, and delete the celebrity, after they are prompted with a warning dialogue.
Upon clicking on one celebrity, the user is navigated to the Celebrity Fragment.

## Industry Fragment

This fragment uses a content provider to query all the industries in which Celebrities may be in and displays them in a Recycler View. From this fragment, the user may tap on one of the industries listed to populate the relevant celebriteis.

## Celebrity Fragment

This fragment is used to add, edit, display and delete celebrity details.

If the user accesses this fragment from the Celebrities fragment, they are shown detailed information about said celebrity. If they click on the edit button, they TextViews with the celebrity's information are hidden and the EditText views are shown, already containing the information showed prior, allowing the user to alter them. Once they are done, they may click on the DONE button.

If the user accesses this fragment from the "Add" button on the navigation drawer, they are prompted with empty EditTexts to fill out details about a new celebrity.

Finally, the user also has available a delete button, which will allow them to delete the celebrity they are viewing after being prompted with a warning dialogue.

## Celebrities
![]{https://github.com/ssmobile/CelebrityApp/blob/master/screenshots/celebrity-list.png?raw=true}

## Favorites
![]{https://github.com/ssmobile/CelebrityApp/blob/master/screenshots/favorite.png?raw=true}

Favorites List
![]{https://github.com/ssmobile/CelebrityApp/blob/master/screenshots/favorite.png?raw=true}

## Industries
![]{https://github.com/ssmobile/CelebrityApp/blob/master/screenshots/industries.png?raw=true}

## Warning
![]{https://github.com/ssmobile/CelebrityApp/blob/master/screenshots/warning.png?raw=true}

## Celebrity details
![]{https://github.com/ssmobile/CelebrityApp/blob/master/screenshots/celebrity-details.png?raw=true}

## Celebrity edit
![]{https://github.com/ssmobile/CelebrityApp/blob/master/screenshots/celebrity-edit.png?raw=true}

## Navigation drawer
![]{://github.com/ssmobile/CelebrityApp/blob/master/screenshots/nav-drawer.png?raw=true}
