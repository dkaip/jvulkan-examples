# jvulkan-examples

**jvulkan-examples** is a collection of Java programs that demonstrate the use of the **jvulkan** Java Vulkan SDK project.  The program files are more or less all of the different examples explored in the **Vulkan Tutorial** that can be found online (written in c++).  For more information refer to the [Vulkan Tutorial](https://vulkan-tutorial.com/).  

Do not expect this software to work if you do not have Vulkan drivers available on your 
machine for your graphics card.  It has been running just fine on a Radeon RX460 with 4GB of RAM.

In addition, at the moment you will need a Wayland compositor available as well.  If you are using
Gnome, this is available as the default when you log on to a windows desktop.  I believe there are a 
number of Linux Distros with a Wayland compositor available.  Hopefully before too long, the 
integration of the other <code>WSI</code> options (<code>Android Platform</code>, 
<code>Win32 Platform</code>, <code>XCB Platform</code>, <code>Xlib Platform</code>) will be completed 
and available for use.


## Installation
At the current time this software has only been built and run on a 64-bit Linux distribution (at this point 
Fedora 29 x86_64).  

Also, at the current time the <code>WSI</code> method used is Wayland.

### Prerequisites

You will need to have a local Maven repository.  I believe it will be created automatically at the first use of 
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

## eclipse
I was able to use <code>git</code> to extract the code from the repository.  Once the code was in a 
directory on my machine, in this case <code>./jvulkan-examples</code>, I executed the command 
<code>gradle cleanEclipse eclipse</code>. Next, I started eclipse and performed an 
"import an existing gradle project" into eclipse. It to loaded the project successfully.  

Once loaded, I was able to select a (sub)module, i.e. <code>Test6</code>, then I selected run configurations.
I was able to create a new run configuration that had the proper dependencies already filled in.  Next, in 
the <code>Arguments</code> tab of the run configuration put the following into the VM arguments section:  

<code>-Dvulkan.validation=true -Djvulkan.native.library.path="/home/dkaip/git/jvulkan-natives-Linux-x86_64/Release" -Djvulkan-examples.shader.path="../src/main/resources/" -Djvulkan-examples.textures.path="../src/main/resources/" -Dlog4j.configurationFile="../src/main/resources/log4j2.xml"</code>  

If, for some reason, the <code>Main class:</code> did not get filled in, in this case, you would put 
<code>com.CIMthetics.JVulkanExamples.Test6</code> for its value.  

With a run configuration created you should be able to run the example in either Debug or non-Debug modes 
from inside the eclipse IDE.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  

Please do not reformat the source code for the existing code.  If you make additions, go ahead and format 
the source files as you like.

Please make sure to update tests as appropriate.

## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)