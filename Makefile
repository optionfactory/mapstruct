deploy:
	java --version | grep -q 1.8 || (echo "Use java8" ; exit 1)
	mvn -DaltDeploymentRepository=nexus::default::https://teletraan.opfa:8081/repositories/maven-snapshots/ clean deploy 
