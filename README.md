# Street Fighter 6 Master Rate Calculator

An Android app that allows you to perform calculations related to Master Rate (MR), the Elo ranking in Street Fighter 6.

- [✨ Features](#-features)
- [🖼️ Screenshots](#️-screenshots)
- [🥇 Credit \& Thanks](#-credit--thanks)
- [📲 Installation](#-installation)
  - [🙋🏻‍♀️ Non-Developer Installation](#️-non-developer-installation)
  - [👩🏻‍💻 Developer Installation](#-developer-installation)


## ✨ Features

- **MR versus win/lose calculation**: Add player 1 and player 2 MR to the app to see how much each player stands to win or lose
- **MR reset calculation**: Check any MR value to see what the rebalanced MR will be after the master phase reset

> [!NOTE]
> This is a fan-made app and is not affiliated with Street Fighter 6 or Capcom in any way.

## 🖼️ Screenshots

<img src="screenshots/screenshott-app-icon.png" height="60" />

<img src="screenshots/screenshot-mr-vs-win-lose.png" width="309" />
<img src="screenshots/screenshot-mr-reset.png" width="309" />

## 🥇 Credit & Thanks

- Thanks to **JB~!** for reverse engineering the MR reset calculator and helping with reverse engineering the Elo calculator for MR win/loss
- Thanks to **Gemini** for helping reverse engineer the Elo calculation for the MR win/loss calculator (after lots of prompt massaging)
- Thanks to **Capcom** for making really fun games like Street Fighter 6. *Disclaimer: this tool is not affiliated with them in any way, just thankful for the game existing.*

## 📲 Installation

### 🙋🏻‍♀️ Non-Developer Installation

No immediate plans to set up code signing and distribution unless there's a demand, but if I do, it'd be on Firebase App Distribution.

For now, please see [Developer Installation](#developer-installation) below.

### 👩🏻‍💻 Developer Installation

This app was built with `Android Studio Narwhal | 2025.1.1 Patch 1`, `Build #AI-251.25410.109.2511.13752376, built on July 8, 2025`.

1. Download and install Android Studio from the [official Android Studio website](https://developer.android.com/studio). Set it up as necessary, installing the latest Android SDK, platform tools, and any emulators you want (if you don't have a physical Android device).
2. Perform a Gradle sync to download and install all dependencies. You can open the Gradle sidebar by pressing the elephant icon on the right, and download the dependencies by pressing the down arrow for "Download sources" to download the project dependencies
3. Plug in your Android device. Ensure it's targeted as the device. If you don't have a physical Android device and have installed an emulator during your Android Studio setup, you can ignore this step.
4. Press the play button to run the project. If you have a physical device, ensure the screen is unlocked so the app can start. If you don't, the emulator should start up automatically.
