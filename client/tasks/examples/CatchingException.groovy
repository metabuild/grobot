// prints the local time
try {
	println Date.getDateString()
} catch (Exception e) {
	println "Failed to run 'cause: ${e.getMessage()}" 

}