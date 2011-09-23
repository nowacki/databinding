set INSTALL=caLL mvn -o install:install-file -Dpackaging=jar

%INSTALL% -Dfile=com.ibm.icu.base_4.2.1.v20100412.jar -DgroupId=com.ibm.icu -DartifactId=icu.base -Dversion=4.2.1 
%INSTALL% -Dfile=org.eclipse.core.databinding_1.4.0.I20110111-0800.jar -DgroupId=org.eclipse.core.databinding -DartifactId=databinding -Dversion=1.4.0
%INSTALL% -Dfile=org.eclipse.core.databinding.observable_1.4.0.I20110222-0800.jar -DgroupId=org.eclipse.core.databinding -DartifactId=databinding.observable -Dversion=1.4.0
%INSTALL% -Dfile=org.eclipse.core.databinding.property_1.4.0.I20110222-0800.jar -DgroupId=org.eclipse.core.databinding -DartifactId=databinding.property -Dversion=1.4.0
%INSTALL% -Dfile=org.eclipse.equinox.common_3.6.0.v20110523.jar -DgroupId=org.eclipse.equinox -DartifactId=equinox.common -Dversion=3.6.0
%INSTALL% -Dfile=org.eclipse.ufacekit.core.ubean_1.0.0.201109221741.jar -DgroupId=org.eclipse.ufacekit.core.ubean -DartifactId=ubean -Dversion=1.0.0
%INSTALL% -Dfile=org.eclipse.ufacekit.core.ubean.databinding_1.0.0.201109221741.jar -DgroupId=org.eclipse.ufacekit.core.ubean -DartifactId=ubean.databinding -Dversion=1.0.0

pause
