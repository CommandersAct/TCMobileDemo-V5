# TCDemo – iOS Demo App for CommandersAct Libraries

TCDemo is an iOS demo application that demonstrates how to integrate and implement the **CommandersAct** libraries available on GitHub:  
[https://github.com/CommandersAct/iOSV5](https://github.com/CommandersAct/iOSV5)

This project provides multiple build variants showing how to use **TCConsent**, **TCServerSide**, and optional **Firebase** integrations with different configurations.  
It is intended as a practical reference for developers integrating CommandersAct into their own iOS projects.

---

## 1. Targets Overview

There are **five main Xcode targets** in the TCDemo project.  
Each target demonstrates a specific combination of libraries.

> Flags are used only to differentiate which parts of the code are active for each target.
In your own project, you generally won’t need to use them — they are included in TCDemo solely to enable building multiple targets that demonstrate different CommandersAct library use cases.

| Target | Description | Flags Used |
|--------|--------------|-------------|
| **TCDemo Consent ONLY** | Uses only the **TCConsent** library (without **TCServerSide**) and includes IAB implementation. | TC_USE_CONSENT |
| **TCDemo IAB** | Full implementation using **TCConsent** and **TCServerSide** with IAB integration. | TC_USE_CONSENT, TC_USE_SERVERSIDE |
| **TCDemo IAB Firebase** | Full implementation including Firebase consent mapping and CommandersAct event forwarding. | TC_USE_CONSENT, TC_USE_SERVERSIDE, TC_USE_FIREBASE |
| **TCDemo no IAB** | Implementation using **TCConsent** and **TCServerSide** but without IAB. | TC_USE_CONSENT, TC_USE_SERVERSIDE |
| **TCDemo ServerSide Only** | Implementation with **TCServerSide** only (no **TCConsent**). | TC_USE_SERVERSIDE |

---

## 2. How to Use the Variants

Each variant demonstrates how to integrate specific libraries, configure privacy centers, and observe event behavior.

#### 2.1. Implementation Files

The main logic is divided across three implementation files:

- **TCConsentImplementation.swift**  
- **TCFirebaseImplementation.swift**  
- **TCServerSideImplementation.swift**

These contain key setup and usage examples for each component.  
Refer to them to understand which initialization and configuration steps apply to your use case.

> 💡 Note: Some code blocks are conditionally compiled depending on Swift Compiler Custom Flags.  
> Check which flags are set under your Xcode target **Build Settings → Swift Compiler - Custom Flags**.

---

#### 2.2. Target Dependencies

Verify the correct frameworks and libraries under  
**Target → Frameworks, Libraries, and Embedded Content**  
to ensure your variant links only the necessary components (matching your integration case).

---

#### 2.3. App Entry Point

The main entry file **TCDemoApp.swift** is responsible for calling the appropriate implementation files.  
It provides the initial setup and connects to the correct variant logic.

---

#### 2.4. JSON Configuration Files

The app’s root directory contains several JSON configuration files.  
Not all JSONs are required for every target — verify which ones are relevant to your selected implementation.  
Consult the SDK documentation for more details on each configuration file.

---

## 3. Firebase Integration & Debugging

If you are using the **IAB_FirebaseDebug** variant, follow these steps to enable Firebase support.

#### 3.1. Setup

1. Create or open your project in the [Firebase Console](https://console.firebase.google.com/).  
2. Add your iOS app bundle ID.  
3. Download the **GoogleService-Info.plist** file and include it in your Xcode project.  
4. Add the Firebase dependencies to your project as described in the [Firebase iOS setup guide](https://firebase.google.com/docs/ios/setup).

---

#### 3.2. Debugging Firebase Events

To verify event forwarding and consent mapping:

1. Enable **DebugView** in Firebase using the following command:  
   ```bash
   -FIRDebugEnabled
   ```
2.Launch your app and observe the Xcode console for CommandersAct event logs.

3.Open Firebase Analytics → DebugView to confirm events are received.

Official documentation: [Firebase DebugView for iOS](https://firebase.google.com/docs/analytics/debugview#ios+)


#### 3.3 Forward Consent to Firebase

To forward consent data to Firebase Analytics, follow the steps outlined in the official CommandersAct SDK documentation:

[Forwarding consent to Firebase Analytics](https://github.com/CommandersAct/iosv5/tree/master/TCConsent#forwarding-consent-to-firebaseanalytics-setting-up-gcm)


## 4. Testing Your Setup 

When you run the app in Xcode:

The console will print versions of the initialized libraries.
Each event sent or consent given will appear as log messages in the console.
For Firebase variants, confirm that events appear both in Xcode logs and in Firebase DebugView.

## 5. Additional Resources

For detailed setup and advanced use cases, review the following resources:

Main iOS SDK Documentation: 

[CommandersAct iOS SDK](https://github.com/CommandersAct/iOSV5):

Library-specific README files:

Each library within the SDK (e.g. TCConsent, TCServerSide, etc.) includes its own README file containing configuration examples, integration steps, and additional notes.

## 7. License

This demo is provided for educational and integration purposes.
Refer to the main CommandersAct SDK repository for licensing details.