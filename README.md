# HittaReviews

+ when first opening the app, a splash screen appears, and while an animation runs, the company name is fetched in background
+ it uses a Room DB to initially store one placeholder review for current user, and 3 other dummy reviews that will be displayed in the main screen
+ reviews list is stored in a ViewModel, and that's observed so any change in it refreshes the reviews list content
+ the average review is refreshed each time the list of reviews changes
+ a placeholder is initially added for user's own review, and once he chooses a rating in the rating bar, a new screen opens up where he can fill in the details (his name and review content)
+ after that, his review is always persisted, either if he presses save (in this case a name validation is also performed), otherwise the review is still persisted if the page closes as it is
+ when his review is updated, the location review and reviews count is also updated
+ each review is saved in the Hitta Sandbox (not yet working)
+ the UI is pretty close to the specs, I've tried using all kinds of layouts for it, but I didn't get the chance to make it pixel perfect (I got stuck a bit in some Retrofix/Room issues which took a bit longer than planned)
