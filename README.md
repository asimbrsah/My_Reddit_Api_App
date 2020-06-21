# Reddit api based sample android app

## This project is a great example of powerful MVVM Architecture pattern. below are the some latest libraries used to build this project...

- Dagger2 (It is being used for injecting dependencies wherever required).
- Retrofit (It is being used to make network call easier and maintainable).
- Rx-java/ Rx-android (It is a great example of handling thread safe api call).
- Interceptor (HttpLoggingInterceptor is being used for logging on the top of Network layer, we can always create custom Interceptor and apply on the top of network layer. for example Tokenization, Api Localization ..etc can be handled from here).
- Timber (It is being used for printing log, based on the build types. for example the custom TimberTree for release build type).
- Glide (For fetching the images and GIFs from network/ local Glide is being used).
- Room Database (Great exchange of SQLite Database is Room Persistence library which provides compile time error and much more flexibility to use in our project).
- Gson (It is a type of converter being used for serialization and deserialization)
- Google android material design (provides some great material designs to use in our projects).
- ExoPlayer (Media player library used for Playing the video and audio with more customization).
- MultiDex is enabled prior to Android 5.0.
- Proguard has been used to Shrink, obfuscate, and optimize the apk.
- Navigation component has been used for better navigation between pages through navigation graph.
- Data Binding is used to reduce the boiler plate code for finding the view reference and directly bind the view with live data.

## Prerequisite for the project
- Android studio 3.3 or higher.
- Create your own store property file.
- Use the Reddit Api base url and add the value for "staging_base_url" or "production_base_url" in store file.
- for pagination add the default start key value for "default_category_key_value" variable in store file
- for remaining constants values it is optional to use from store file, we can also use it directly from gradle file however it is preferred to use ".properties" file for keystore credentials and other sensitive information.


## Note

- This project is written in Java language (Will published Kotlin too in upcoming week).
- To build this project latest MVVM Architecture pattern has been followed.
- The Build system has been configured in such a way to provide security for the project.(Referred from official android websites).


## Blog Resources

- https://developer.android.com/
- https://blog.mindorks.com/
- https://medium.com/topic/android-development
- https://guides.codepath.com/android
- https://proandroiddev.com/exploring-rxjava-in-android-e52ed7ef32e2
- https://medium.com/@harivigneshjayapalan/dagger-2-for-android-beginners-dagger-2-part-i-f2de5564ab25
- https://www.youtube.com/watch?v=3qZh6Fyrz-k&list=PLgCYzUzKIBE8AOAspC3DHoBNZIBHbIOsC