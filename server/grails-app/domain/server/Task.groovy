package server

class Task {
	
	String name
	String code
	List<Object> arguments
	
	static hasMany = [ dependencies : Task ]

    static constraints = {
		name(blank: false, maxSize: 40)
		code(blank: false, maxSize: 1000)
		arguments(nullable: true)
		dependencies(nullable: true)
    }
	
	String toString() {
		name
	}
}
