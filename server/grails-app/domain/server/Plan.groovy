package server

class Plan {

	String name
	Plan parent
	
	static hasMany = [ tasks : Task ]
	
    static constraints = {
		name(blank: false, maxSize: 40)
    }
	
	String toString() {
		name
	}
}
