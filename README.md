# log4j2-springboot-plugins-issue

Steps to reproduce issue:
1. Install jar of log4j-plugin-holder module with maven profile 'annotationProcOff' into local repo
2. Run 'Sample App' application from IDE or maven plugin. Expected result is <SamplePlugin/> logger layout plugin found.
3. Build a fat jar and run. Expected result is plugin is not found: "ERROR Console contains an invalid element or attribute 'SamplePlugin'"

If we would change first step to build artifact with annotation processsing ON, everything would work. 
But according to the Log4j2 documentation - https://logging.apache.org/log4j/2.x/manual/plugins.html:

_In Log4j 2 a plugin is declared by adding a @Plugin annotation to the class declaration. During initialization the Configuration will invoke the PluginManager to load the built-in Log4j plugins as well as any custom plugins. The PluginManager locates plugins by looking in five places_:

1. _Serialized plugin listing files on the classpath. These files are generated automatically during the build (more details below)._

2. _(OSGi only) Serialized plugin listing files in each active OSGi bundle. A BundleListener is added on activation to continue checking new bundles after log4j-core has started._

3. _A comma-separated list of packages specified by the log4j.plugin.packages system property._

4. _Packages passed to the static PluginManager.addPackages method (before Log4j configuration occurs)._

5. _**The packages declared in your log4j2 configuration file.**_

So, It seems, last fifth point in bold doesn't work with custom spring boot jar classloading system.
