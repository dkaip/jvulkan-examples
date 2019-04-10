# jvulkan-examples

<b>jvulkan-examples</b> is a collection of Java programs that demonstrate the use of the <b>jvulkan</b> Java Vulkan SDK project.  The program files are more or less all of the different examples explored in the <b>Vulkan Tutorial</b> that can be found online (in c++).  For more information refer to the [Vulkan Tutorial](https://vulkan-tutorial.com/).

## Installation


In addition to this library you will need to retrieve and build the 
jvulkan-natives-Linux-x86_64 project and the jvulkan project.

Hopefully before too long jvulkan and the natives will be available in
jcenter and / or maven central.

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```

## Running the examples

<code>java -Dvulkan.validation=true -Djava.library.path="$LD_LIBRARY_PATH" -Djvulkan.native.library.path="/home/dkaip/git/jvulkan-natives-Linux-x86_64/Release" -Djvulkan-examples.shader.path="/home/dkaip/JavaWorkspaces/CIMthetics/hwjviClient/src/main/resources/" myprog</code>

If you would like to see debug information for Wayland set the <code>WAYLAND_DEBUG</code> environment variable to the value of <code>1</code>.

```python
import foobar

foobar.pluralize('word') # returns 'words'
foobar.pluralize('goose') # returns 'geese'
foobar.singularize('phenomena') # returns 'phenomenon'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)