An Android app for getting All News

Note: the https://newsapi.org/ return maximum of 100 items in free Plan (Developer accounts are limited to a max of 100 results) So in the news list, the max results 100

the app contains 2 screens 
1- news screen that shows List of all the news items with infinite scrolling each item display image,title,author and publishedAt
2- news details screen that shows image,title,author,description,content and publishedAt


Main libraries and tools used

Koin (DI)
MVVM (Architecture)
Paging Library (Paging)
Retrofit (Network)
Coroutines
Glide

Testing
For Repo Unit testing I have been using (https://github.com/square/okhttp/tree/master/mockwebserver) to mock the response from the API.