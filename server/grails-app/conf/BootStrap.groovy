import server.TargetGroup
import server.TargetNode
import server.Task
import server.Plan
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
		if (Environment.getCurrent()  == Environment.DEVELOPMENT) {
			if (!TargetNode.count()) {
				new TargetNode(hostname: 'localhost', active: true).save(failOnError: true)
				new TargetNode(hostname: 'jburbridge-dev1', active: true).save(failOnError: true)
				new TargetNode(hostname: 'grobot-master1-1', active: true).save(failOnError: true)
				new TargetNode(hostname: 'grobot-master1-2', active: true).save(failOnError: true)
				new TargetNode(hostname: 'grobot-minion1-1', active: true).save(failOnError: true)
				new TargetNode(hostname: 'grobot-minion1-2', active: true).save(failOnError: true)
				new TargetNode(hostname: 'grobot-minion1-3', active: true).save(failOnError: true)
				new TargetNode(hostname: 'grobot-minion1-4', active: true).save(failOnError: true)
			}
			if (!TargetGroup.count()) {
				new TargetGroup(name: 'default', nodes: TargetNode.getAll()).save(failOnError: true)
				new TargetGroup(name: 'group1', nodes: TargetNode.getAll()).save(failOnError: true)
				new TargetGroup(name: 'group2', nodes: TargetNode.getAll()).save(failOnError: true)
				new TargetGroup(name: 'childGroup', parent: TargetNode.get(0)).save(failOnError: true)
			}
			if (!Task.count()) {
				new Task(name: 'ping', code: 'println "ping!"').save(failOnError: true)
				new Task(name: 'pong', code: 'println "pong!"', dependencies: [Task.get(0)]).save(failOnError: true)
				new Task(name: 'grailsVersion', code: 'println "${grails.util.GrailsUtil.grailsVersion}"').save(failOnError: true)
			}
			if (!Plan.count()) {
				new Plan(name: 'testPlan', tasks: Task.getAll()).save(failOnError: true)
			}
		}
    }
    def destroy = {
    }
}
