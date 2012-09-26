package server

class TargetNode {
	
	String hostname
	Boolean active
	
	static belongsTo = [ TargetGroup ]

    static constraints = {
		hostname(blank: false, maxSize: 80)
    }
	
	String toString() {
		hostname
	}
}

