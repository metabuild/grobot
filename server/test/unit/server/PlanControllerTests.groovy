package server



import org.junit.*
import grails.test.mixin.*

@TestFor(PlanController)
@Mock(Plan)
class PlanControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/plan/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.planInstanceList.size() == 0
        assert model.planInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.planInstance != null
    }

    void testSave() {
        controller.save()

        assert model.planInstance != null
        assert view == '/plan/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/plan/show/1'
        assert controller.flash.message != null
        assert Plan.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/plan/list'

        populateValidParams(params)
        def plan = new Plan(params)

        assert plan.save() != null

        params.id = plan.id

        def model = controller.show()

        assert model.planInstance == plan
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/plan/list'

        populateValidParams(params)
        def plan = new Plan(params)

        assert plan.save() != null

        params.id = plan.id

        def model = controller.edit()

        assert model.planInstance == plan
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/plan/list'

        response.reset()

        populateValidParams(params)
        def plan = new Plan(params)

        assert plan.save() != null

        // test invalid parameters in update
        params.id = plan.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/plan/edit"
        assert model.planInstance != null

        plan.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/plan/show/$plan.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        plan.clearErrors()

        populateValidParams(params)
        params.id = plan.id
        params.version = -1
        controller.update()

        assert view == "/plan/edit"
        assert model.planInstance != null
        assert model.planInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/plan/list'

        response.reset()

        populateValidParams(params)
        def plan = new Plan(params)

        assert plan.save() != null
        assert Plan.count() == 1

        params.id = plan.id

        controller.delete()

        assert Plan.count() == 0
        assert Plan.get(plan.id) == null
        assert response.redirectedUrl == '/plan/list'
    }
}
