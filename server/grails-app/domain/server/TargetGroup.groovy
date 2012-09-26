package server

class TargetGroup {

	String name
	TargetGroup parent
	
	static hasMany = [ nodes : TargetNode]
	
    static constraints = {
		name(blank: false, maxSize: 40)
		parent(nullable: true)
    }
	
	String toString() {
		name
	}
}
