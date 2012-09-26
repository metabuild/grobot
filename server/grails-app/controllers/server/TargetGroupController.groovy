package server

import org.springframework.dao.DataIntegrityViolationException

class TargetGroupController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [nodeGroupInstanceList: TargetGroup.list(params), nodeGroupInstanceTotal: TargetGroup.count()]
    }

    def create() {
        [nodeGroupInstance: new TargetGroup(params)]
    }

    def save() {
        def nodeGroupInstance = new TargetGroup(params)
        if (!nodeGroupInstance.save(flush: true)) {
            render(view: "create", model: [nodeGroupInstance: nodeGroupInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'nodeGroup.label', default: 'NodeGroup'), nodeGroupInstance.id])
        redirect(action: "show", id: nodeGroupInstance.id)
    }

    def show(Long id) {
        def nodeGroupInstance = TargetGroup.get(id)
        if (!nodeGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nodeGroup.label', default: 'NodeGroup'), id])
            redirect(action: "list")
            return
        }

        [nodeGroupInstance: nodeGroupInstance]
    }

    def edit(Long id) {
        def nodeGroupInstance = TargetGroup.get(id)
        if (!nodeGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nodeGroup.label', default: 'NodeGroup'), id])
            redirect(action: "list")
            return
        }

        [nodeGroupInstance: nodeGroupInstance]
    }

    def update(Long id, Long version) {
        def nodeGroupInstance = TargetGroup.get(id)
        if (!nodeGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nodeGroup.label', default: 'NodeGroup'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (nodeGroupInstance.version > version) {
                nodeGroupInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'nodeGroup.label', default: 'NodeGroup')] as Object[],
                          "Another user has updated this NodeGroup while you were editing")
                render(view: "edit", model: [nodeGroupInstance: nodeGroupInstance])
                return
            }
        }

        nodeGroupInstance.properties = params

        if (!nodeGroupInstance.save(flush: true)) {
            render(view: "edit", model: [nodeGroupInstance: nodeGroupInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'nodeGroup.label', default: 'NodeGroup'), nodeGroupInstance.id])
        redirect(action: "show", id: nodeGroupInstance.id)
    }

    def delete(Long id) {
        def nodeGroupInstance = TargetGroup.get(id)
        if (!nodeGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nodeGroup.label', default: 'NodeGroup'), id])
            redirect(action: "list")
            return
        }

        try {
            nodeGroupInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'nodeGroup.label', default: 'NodeGroup'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'nodeGroup.label', default: 'NodeGroup'), id])
            redirect(action: "show", id: id)
        }
    }
}
