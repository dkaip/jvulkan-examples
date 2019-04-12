# jvulkan-examples

**jvulkan-examples** is a collection of Java programs that demonstrate the use of the **jvulkan** Java Vulkan SDK project.  The program files are more or less all of the different examples explored in the **Vulkan Tutorial** that can be found online (written in c++).  For more information refer to the [Vulkan Tutorial](https://vulkan-tutorial.com/).

## Installation
At the current time this software has only been built and run on a 64-bit Linux distribution (at this point 
Fedora 29 x86_64).  

Also, at the current the <code>WSI</code> method used is Wayland.

### Prerequisites

You will need to have a local Maven repository.  I believe it will be created at the first use of 
any Maven(<code>mvn</code>) command.  

Retrieve and build the <code>jvulkan-natives-Linux-x86_64</code> project.  Refer to its 
README file for information. 
Once built, locate the result of the build, the file <code>libjvulkan-natives-Linux-x86_64.so</code>, 
and <code>cd</code> to that directory.  Once in that directory execute the following maven command:  

<code>mvn install:install-file -Dfile=./libjvulkan-natives-Linux-x86_64.so -DgroupId="com.CIMthetics" -DartifactId=libjvulkan-natives-Linux-x86_64 -Dversion="0.0.1" -Dpackaging=so</code>  

This will install the <code>libjvulkan-natives-Linux-x86_64.so</code> artifact into the local Maven 
repository so that the <code>gradle</code> build process will be able to access it.

Retrieve and build the <code>jvulkan</code> project.  Refer to its 
README file for information. Once built, locate the result of the build, the file <code>jvulkan.jar</code>, 
and <code>cd</code> to that directory. Once in that directory execute the following maven command:

<code>mvn install:install-file -Dfile=./jvulkan.jar -DgroupId="com.CIMthetics" -DartifactId=jvulkan -Dversion="0.0.1" -Dpackaging=jar</code>  

This will install the <code>jvulkan.jar</code> artifact into the local Maven 
repository so that the <code>gradle</code> build process will be able to access it.

In order to run examples <code>Test6</code> - <code>Test9</code> you will also need to retrieve and build the 
<code>lwjgl3-demos.jar</code> project. 

Hopefully before too long <code>jvulkan</code> and the native (<code>libjvulkan-natives-Linux-x86_64.so</code>) will be available in jcenter and / or maven central and you will not have to download and build these for yourself.

### Get the code for the examples
Use the <code>git clone</code> command to get the code. 

<code>git clone path-to-repository jvulkan-examples</code> 

This will create a <code>jvulkan-examples</code> directory in your current directory

## Running the examples

Now <code>cd</code> to the <code>jvulkan-examples</code> directory and execute the following commands:  

<code>gradle clean</code>
<br>
<code>gradle run</code> 

This should run all of the example programs one at a time. 

If you would like to see debug information for Wayland, set the <code>WAYLAND_DEBUG</code> environment variable to the value of <code>1</code> (one) before you execute the <code>gradle run</code> command.  In order to stop the Wayland 
debug information set the <code>WAYLAND_DEBUG</code> environment variable to the value of <code>0</code> (zero) 
before you execute the <code>gradle run</code> command.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  

Please do not reformat the source code for the existing code.  If you make additions, go ahead and format 
the source files as you like.

Please make sure to update tests as appropriate.

## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)