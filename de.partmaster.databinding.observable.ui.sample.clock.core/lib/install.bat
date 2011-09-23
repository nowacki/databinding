set INSTALL=caLL mvn -o install:install-file -Dpackaging=jar

%INSTALL% -Dfile=com.ibm.icu.base_4.2.1.v20100412.jar -DgroupId=com.ibm.icu -DartifactId=icu.base -Dversion=4.2.1 
:%INSTALL% -Dfile=org.eclipse.core.databinding_1.4.0.I20110111-0800.jar -DgroupId=org.eclipse.core.databinding -DartifactId=databinding -Dversion=1.4.0
%INSTALL% -Dfile=org.eclipse.core.databinding-1.4.0.jar -DgroupId=org.eclipse.core.databinding -DartifactId=databinding -Dversion=1.4.0
%INSTALL% -Dfile=org.eclipse.core.databinding.observable-1.4.0.jar -DgroupId=org.eclipse.core.databinding -DartifactId=databinding.observable -Dversion=1.4.0
%INSTALL% -Dfile=org.eclipse.core.databinding.property-1.4.0.jar -DgroupId=org.eclipse.core.databinding -DartifactId=databinding.property -Dversion=1.4.0
%INSTALL% -Dfile=org.eclipse.equinox.common-3.6.0.jar -DgroupId=org.eclipse.equinox -DartifactId=equinox.common -Dversion=3.6.0
%INSTALL% -Dfile=org.eclipse.ufacekit.core.ubean_1.0.0.201109221741.jar -DgroupId=org.eclipse.ufacekit.core.ubean -DartifactId=ubean -Dversion=1.0.0
%INSTALL% -Dfile=org.eclipse.ufacekit.core.ubean.databinding_1.0.0.201109221741.jar -DgroupId=org.eclipse.ufacekit.core.ubean -DartifactId=ubean.databinding -Dversion=1.0.0
%INSTALL% -Dfile=org.eclipse.ufacekit.ui.swing.databinding.jar -DgroupId=org.eclipse.ufacekit.ui.swing -DartifactId=swing.databinding -Dversion=1.0.0
%INSTALL% -Dfile=org.eclipse.swt.win32.win32.x86_3.7.0.v3735b.jar -DgroupId=org.eclipse -DartifactId=swt -Dversion=3.7.0-win32-win32-x86
%INSTALL% -Dfile=org.eclipse.jface.databinding_1.5.0.I20100907-0800.jar -DgroupId=org.eclipse -DartifactId=jface.databinding -Dversion=1.5.0
%INSTALL% -Dfile=org.eclipse.jface_3.7.0.I20110522-1430.jar -DgroupId=org.eclipse -DartifactId=jface -Dversion=3.7.0
%INSTALL% -Dfile=org.eclipse.core.commands_3.6.0.I20110111-0800.jar -DgroupId=org.eclipse.core -DartifactId=commands -Dversion=3.6.0

pause
