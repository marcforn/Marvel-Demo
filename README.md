# Documentation

### Third party libraries
Next I have detailed the 3rd party libraries used in the test project and the pros and cons of everyone.
* [Dagger2] - To avoid instantiating the same object several times we need dependency-injecton, so I have used Dagger2 to inject the dependencies and work according to Clean-Architecture pattern.
  - Pros: Having objects already injected to the graph allows you to only have one instances of them, which means less amount of memory required by the app.
  - Cons: To use dependency injection you need to spend some time to learn and understand how it works and how to implement it properly.
  
* [RxJava/RxAndroid] - This libray is an extension of Reactive. Working with reactive pattern allows us to work with observables which emits information and subscribers that are kind of “callbacks” ready to listen on any observable emission.
  - Pros: Using RxJava on Clean Architecture allows us to communicate all the layer of the apps without having to use a big ammount of interfaces.
  - Cons: To start working with RxJava you need to spend a big amount of time to fully understand how it works and how to use it properly.

* [Retrolambda] (Java 8 lambdas) - This library allows you to use Java8 lambdas in Android projects.

  - Pros: When working with nested class as RxJava this library simplifies the code in the way it is more easy to read and understand.
  - Cons: We have to be aware we are using a “patch” that simulates we are using Java8, so we cannot use all the power of Java 8, only the lambdas.
  
* [Retrofit2] - This library created by Square is the evolution of the Retrofit library, the most used in Android universe to handle REST requests.

  - Pros: Implementing a simple interface you have a REST request implemented. You do not have to worry about HTTP connections, Retrofit handles all the connection stuff. Also, you can process the response as an Observable which helps you a lot with the clean architecture pattern.
  - Cons: To use all the strength of the library you need to spend some time to learn how it works.
  
* [Ormlite] - This ORM library allows you to work on a higher layer from DDBB. Using Ormlite you don't have to worry about openhelpers creation and modification.

  - Pros: Simplify the use of SQLite database in the application.
  - Cons: It's based on Sqlite which means on big projects the performance is not the desired one. In bigger projects I encourage the use of Realm for Android.
 
* [Glide] - This libray allows you to cache images from internet and show them in the app views.

  - Pros: Has better cache behaviour than Picasso and simplify the images loader process.
  - Cons: Not cons found for the moment.
  
* [Stetho] (Only for developer purpose) - A libray created by facebook that let's you to acces to the application information while it's running. This library allows you to access to SharedPreferences and Database to CRUD elements from it. Also allows you to track all the requests done by the app.

  - Pros: Allows you to access to the app internal data.
  - Cons: When activated the app is getting slower and sometimes is hard to debug.

* [Leak Canary] (Only for developer purpose) - This library created by Square allows you to know when some class is leaked by the app and helps you to remove stuck memory.

  - Pros: Allows you to fix the memory leaks.
  - Cons: Sometimes with Stetho running at same time it throwns fake memory leaks.
  
### Unit testing
Due the giving time, was not be possible to implement Unit testing on the test app. 

Anyway it is easy to implement due the use of the clean architecture pattern:
  - For testing the presentation layer we could use Espresso.
  - For testing the domain and data layer we could use Mockito and junit.
