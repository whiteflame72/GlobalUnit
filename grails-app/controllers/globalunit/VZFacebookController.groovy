package globalunit

import org.springframework.dao.DataIntegrityViolationException

class VZFacebookController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [VZFacebookInstanceList: VZFacebook.list(params), VZFacebookInstanceTotal: VZFacebook.count()]
    }

    def create() {
        [VZFacebookInstance: new VZFacebook(params)]
    }

    def save() {
        def VZFacebookInstance = new VZFacebook(params)
        if (!VZFacebookInstance.save(flush: true)) {
            render(view: "create", model: [VZFacebookInstance: VZFacebookInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'VZFacebook.label', default: 'VZFacebook'), VZFacebookInstance.id])
        redirect(action: "show", id: VZFacebookInstance.id)
    }

    def show() {
        def VZFacebookInstance = VZFacebook.get(params.id)
        if (!VZFacebookInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'VZFacebook.label', default: 'VZFacebook'), params.id])
            redirect(action: "list")
            return
        }

        [VZFacebookInstance: VZFacebookInstance]
    }

    def edit() {
        def VZFacebookInstance = VZFacebook.get(params.id)
        if (!VZFacebookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'VZFacebook.label', default: 'VZFacebook'), params.id])
            redirect(action: "list")
            return
        }

        [VZFacebookInstance: VZFacebookInstance]
    }

    def update() {
        def VZFacebookInstance = VZFacebook.get(params.id)
        if (!VZFacebookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'VZFacebook.label', default: 'VZFacebook'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (VZFacebookInstance.version > version) {
                VZFacebookInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'VZFacebook.label', default: 'VZFacebook')] as Object[],
                          "Another user has updated this VZFacebook while you were editing")
                render(view: "edit", model: [VZFacebookInstance: VZFacebookInstance])
                return
            }
        }

        VZFacebookInstance.properties = params

        if (!VZFacebookInstance.save(flush: true)) {
            render(view: "edit", model: [VZFacebookInstance: VZFacebookInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'VZFacebook.label', default: 'VZFacebook'), VZFacebookInstance.id])
        redirect(action: "show", id: VZFacebookInstance.id)
    }

    def delete() {
        def VZFacebookInstance = VZFacebook.get(params.id)
        if (!VZFacebookInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'VZFacebook.label', default: 'VZFacebook'), params.id])
            redirect(action: "list")
            return
        }

        try {
            VZFacebookInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'VZFacebook.label', default: 'VZFacebook'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'VZFacebook.label', default: 'VZFacebook'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
