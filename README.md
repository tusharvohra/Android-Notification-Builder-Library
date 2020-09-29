# Android-Notification-Builder-Library
Android Library to create and customise notifications easily

## Get the library in your App

### Step1: Add the JitPack repository to your build file:
#### Gradle:
Add it in your root build.gradle at the end of repositories:
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### Maven:
```java
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

### Step2: Add the dependency
#### Gradle:
```java
dependencies {
	implementation 'com.github.tusharvohra:Android-Notification-Builder-Library:v1.0.0'
}
```

#### Maven:
```java
<dependency>
    <groupId>com.github.tusharvohra</groupId>
    <artifactId>Android-Notification-Builder-Library</artifactId>
    <version>v1.0.0</version>
</dependency>
```

## Use it in your App
1. Initialize an instance of the NotificationManager.Builder() by passing the context,your notification channel ID and notification channel name
2. Use the various methods to create and cusomize your notification
3. Call .build() to return a notification instance
example:This code will return a notification type variable with a title and content-
```kotlin
 val notification = NotificationManager.Builder(this, "Notification_Channel_Id", "Notification_Channel_Name)
            .notificationTitle("Notification Title")
            .notificationContent("Notification Content")
            .smallIcon(R.drawable.ic_notification)
            .build()
```
You can also use it to create other styles of notifications like BigPicture style and BigText style.
