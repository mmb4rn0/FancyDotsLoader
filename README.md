# FancyDotsLoader

This loader is mainly based on animating user defined dots and shapes.

[![Alt text for your video](https://github.com/mmb4rn0/FancyDotsLoader/blob/master/website/fancy_dots_loader.gif)]

[![](https://jitpack.io/v/mmb4rn0/FancyDotsLoader.svg)](https://jitpack.io/#mmb4rn0/FancyDotsLoader)

# Download
Grab via gradle-
  Step 1. Add the JitPack repository to your build file
  ```grovy
   allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```  
  Step 2. Add the dependency 
  ```grovy	
   dependencies {
	        implementation 'com.github.mmb4rn0:FancyDotsLoader:v1.1.0'
	}
  ```
or via Maven-
  Step 1. Add the JitPack repository to your build file
  ```xml
   <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  ```
  Step 2. Add the dependency
  ```xml
   <dependency>
	    <groupId>com.github.mmb4rn0</groupId>
	    <artifactId>FancyDotsLoader</artifactId>
	    <version>v1.1.0</version>
	</dependency>
  ```

# Sample Code
```xml
    <com.mmbarno.dotsloader.DotsLoader
        android:id="@+id/dot_loader_3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:dots_color="@color/colorAccent"
        app:dots_size="20dp"
        app:dots_count="4"
        app:dots_stroke_width="2dp"
        app:dots_spacing="8dp"
        app:dots_corner_radius="4dp"
        app:dots_transition_duration="650"/>
   ```
   
   # License
   
   MIT License

Copyright (c) 2018 Manzur E Mehedi Barno

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
