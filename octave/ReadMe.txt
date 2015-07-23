Wrapper to run OCTAVE inside JAVA
https://kenai.com/projects/javaoctave/pages/Home

1) CallOctave.java has some examples
javac -cp .:javaoctave-0.6.4.jar:junit-4.12.jar:commons-logging-1.2.jar:commons-math3-3.5.jar CallOctave.java 
java -cp .:javaoctave-0.6.4.jar:junit-4.12.jar:commons-logging-1.2.jar:commons-math3-3.5.jar CallOctave

2) CallNonlinearFit.java is JAVA class for fitting to nonlinear model with Levenberg Marquardt routine
javac -cp .:javaoctave-0.6.4.jar:junit-4.12.jar:commons-logging-1.2.jar:commons-math3-3.5.jar CallNonlinearFit.java 
java -cp .:javaoctave-0.6.4.jar:junit-4.12.jar:commons-logging-1.2.jar:commons-math3-3.5.jar CallNonlinearFit

note: for Windows use ; insead of : in class path

- javaoctave is the API to connect to OCTAVE, it depends on commons-logging

- commons-math only needed for some output (using ArrayRealVector), we can omit it for 2)!

- OCTAVE installation required, as well as the optim package and stuct package

- some *.m files required, and also required: hitran data files *.csv

- passing Objects to OCTAVE (Strings, Doubles, Integers or Arrays) goes via Strings which look like "VariableName= Object"
  check initializeHitran and getFtirData in CallNonlinearFit.java

- for each recorded ftir spectrum we need to output the fitted double value "ppms_hitran_sum" = "mixing ratio from hitran sum" displayed as number in ppm units


----------------------------------------------------------------------------------------------------------------------------
Install packages for OCTAVE: on octave command line prompt do
  pkg install -forge packge-name (without tar.gz and version number)
  for example
  pkg install -forge optim

  beware of dependencies: for 'optim' it is 'struct'

note:
to make functions inside the package usable from command prompt do
  pkg load package_name
  for example
  pkg load optim
