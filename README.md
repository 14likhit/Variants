# Variants

Variants is demo job listing app. Variants can be used using following three filters:

  - Companies
  - Job titles
  - Locations


### Tech

Variants is made using following libraries and Architectural Pattern:

* [MVVM] - Android Architechtural component ViewModels and Live Data are used to implement this. 
* [DataBinding] - Used Databinding
* [Retrofit] - Retrofit is used for Network Requests.
* [Picasso] - Picasso is used to load images.

### Installation

Download the apk and install the app.

### Development (Tech Logic)

-Fetch the data from API
Sample API:
```sh
{
    "data":{
        "data_groups":[
            "group1":{}
            ...
            ...
            ...
        ]
        "excluded_data_map":[
            [
                {}
                {}
            ]
            [
                {}
                {}
            ]
        ]
    }
}
```
-Load Data Groups in Recycler View
-Build Excluding group Mapping. Here we have used helper class to map considering map will be always a pair of two variants.
-Display Selections considering Exclusion Map.

### Todos

 - Implement Easy Navigation
 - Implement filter
