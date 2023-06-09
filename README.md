# Android Project 4 - *Flixster+ Part 2*

Submitted by: **Sharon Chen**

**Flixster+** is a movie browsing app that allows users to browse popular TV shows and movies

Time spent: **14** hours spent in total for required and optional features

## Required Features

The following **required** functionality is completed:

- [x] **Choose any endpoint on The MovieDB API except `now_playing`**
  - Chosen Endpoint: `/tv/popular and /movie/popular`
- [x] **Make a request to your chosen endpoint and implement a RecyclerView to display all entries**
- [x] **Use Glide to load and display at least one image per entry**
- [x] **Click on an entry to view specific details about that entry using Intents**

The following **optional** features are implemented:

- [x] **Add another API call and RecyclerView that lets the user interact with different data.**
- [x] **Add rounded corners to the images using the Glide transformations**
- [x] **Implement a shared element transition when user clicks into the details of a movie**

The following **additional** features are implemented:

- [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://user-images.githubusercontent.com/69126372/226150778-85a19cc9-44a7-4209-a71a-a98d05d2ea49.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

<!-- Replace this with whatever GIF tool you used! -->
GIF created with AZ Screen Recorder (on Android phone) and ScreenToGif to screen recording and convert to gif
<!-- Recommended tools:
[Kap](https://getkap.co/) for macOS
[ScreenToGif](https://www.screentogif.com/) for Windows
[peek](https://github.com/phw/peek) for Linux. -->

## Notes

Describe any challenges encountered while building the app.

1. How to use Serialization library is somewhat different from the lab. Took some time to debug and figure it out.
2. Bugs occured when implementing shared elements transition: had error in Pair type, and incorrectly used MainActivity instead of just using this.activity
3. Adding another API call required separate recylerview and adapter, but can have only one fragment. But still have bug that headers not moving when scrolling up

## License

    Copyright [2023] [Sharon Chen]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
