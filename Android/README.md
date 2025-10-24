# TCDemo

**TCDemo** is an Android demo application showcasing how to implement the [CommandersAct Android SDK libraries](https://github.com/CommandersAct/AndroidV5).  
It provides multiple build variants to demonstrate different integration setups for consent management and event tracking.

---

## 1. Overview

This demo project helps developers understand how to:
- Integrate **CommandersAct SDKs** (`TCConsent`, `TCServerSide`, etc.)
- Configure **IAB** (Transparency & Consent Framework) implementations
- Use **Firebase** for consent mapping and event forwarding
- Explore and compare different combinations of library usage through build variants

---

## 2. Build Variants

There are **five main build variants** in this project.  
Make sure to select the correct variant in **Android Studio** before building or running the app.

| Variant | Description |
|----------|--------------|
| **Consent_OnlyDebug** | Uses only the `TCConsent` library. No `TCServerSide`. Implements IAB. |
| **IABDebug** | Full implementation with `TCConsent` + `TCServerSide` using IAB. |
| **IAB_FirebaseDebug** | Full implementation with `TCConsent` + `TCServerSide` + Firebase consent mapping and Commanders Act event forwarding. |
| **NoIABDebug** | Implementation with `TCConsent` + `TCServerSide`, but **without** IAB. |
| **ServerSide_OnlyDebug** | Uses only the `TCServerSide` library, **no `TCConsent`** implementation. |

---

## 3. Project Structure

When exploring the project, focus on the following key areas:

#### 3.1. **Implementation Classes**
Located in your source directory, the following classes contain the main integration logic:
- `TCConsentImplementation`
- `TCFirebaseImplementation`
- `TCServerSideImplementation`

Each class demonstrates how to properly initialize and use the CommandersAct SDK depending on the selected build variant.  
Use these as reference implementations for integrating the SDK into your own project.

---

#### 3.2. **Gradle Configuration**
Open the **`build.gradle`** files (both at the project and module levels).  
Each build variant specifies which libraries to include.  
Check that your configuration matches your intended use case (e.g., including or excluding `TCServerSide` or Firebase dependencies).

---

#### 3.3. **Application Setup**
The **`TCApplication.kt`** file is the entry point where all the implementations (`TC**Implementation`) are initialized.  
It determines which setup runs depending on the selected build variant.

---

#### 3.4. **Assets & JSON Configuration**
Inside the **`assets/`** folder, you’ll find several JSON files used by different build variants.  
Not all JSON files are required for every setup — refer to the [CommandersAct documentation](https://github.com/CommandersAct/AndroidV5) for details on which configurations are relevant to your use case.

---

## 4. Firebase Integration & Debugging

If you are using the **`IAB_FirebaseDebug`** variant, you’ll need to configure Firebase properly before testing.

#### 4.1 Setup

1. Add your **Firebase project** in the [Firebase Console](https://console.firebase.google.com/).
2. Download your `google-services.json` file.
3. Place it inside your app module directory:  `app/google-services.json`
4. Sync your project with Gradle.

#### 4.2 Debugging Firebase Events

To verify Firebase event forwarding and consent mapping:

- Follow the official Firebase DebugView setup guide:  [Firebase DebugView for Android](https://firebase.google.com/docs/analytics/debugview#android)

Then check Logcat for CommandersAct event and and verify events appear in DebugView on the Firebase console too.

#### 4.3 Forward Consent to Firebase

Check documentation on our github SDK repository : 

[Forwarding consent to Firebase Analytics](https://github.com/CommandersAct/AndroidV5/tree/master/TCConsent#forwarding-consent-to-firebaseanalytics-)


## 5. How to Test

Run the app using your chosen build variant. Open Logcat in Android Studio.

Use the filter: `commandersact`

You should see logs indicating:

- Library versions being initialized.
- Consent being collected or updated.
- Events being sent to the CommandersAct platform.
- (Optional) Firebase consent mapping and forwarded events if using the Firebase variant.

## 6. Additional Resources

It is important to review the official documentation for detailed setup and integration guidance.  
Make sure to check both:

- The **main Android SDK documentation**:  
  [CommandersAct Android SDK](https://github.com/CommandersAct/AndroidV5)
- The **README files inside each individual library** you are using within that repository.  
  Each library (e.g. `TCConsent`, `TCServerSide`, etc.) includes specific instructions, configuration details, and implementation notes.


## 7. License

This demo is provided for educational and integration purposes.
Refer to the main CommandersAct SDK repository for licensing details.