# BottomNavigation
An implemention of bottom navaigation.

![gif](https://raw.githubusercontent.com/ChrisCheng4j/BottomNavigation/master/img/demo.gif)
##Integration
Add the dependencies to your gradle file:
```javascript
dependencies {
    compile 'com.chrischeng:bottomnavigation:1.0.2'
}
```
##XML Usage
-    Use directly:
```xml
<com.chrischeng.bottomnavigation.BottomNavigation
        android:id="@+id/bot_nav"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```
-   Current Attributes supported:
```xml
<declare-styleable name="BottomNavigation">
        <attr name="bn_tab_icon_width" format="dimension" />
        <attr name="bn_tab_icon_height" format="dimension" />
        <attr name="bn_tab_text_size" format="dimension" />
        <attr name="bn_tab_text_color" format="color" />
        <attr name="bn_tab_text_top_margin" format="dimension" />
        <attr name="bn_tab_vp_limit" format="integer" />
    </declare-styleable>
```
##Java Usage
```java
nav.setCurrentItem(0); // default 0
nav.setScrollable(true); // default true
nav.setOffScreenPageLimit(1); // default 1
```
## License
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
